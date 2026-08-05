package com.androiddevelopersguide.ui.screens.databasehandling

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section
import com.androiddevelopersguide.ui.navigation.NavRouter

@Composable
fun DatabaseHandlingScreen(
    navController: NavController,
    viewModel: DatabaseHandlingViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Database handling",
        mobileUI = {
            Introduction(navController = navController)
            RoomOverview()
            Entity()
            Dao()
            AppDatabase()
            RepositoryIntegration()
            DependencyInjection()
            Migrations()
        }
    )
}

@Composable
fun Introduction(navController: NavController) {
    Section(
        title = "What & why",
        description = """
            A local database lets the app keep working when the network is slow, absent, or returning stale data. On Android the standard answer is Room — a SQLite wrapper that generates query implementations from annotated Kotlin interfaces, so you write SQL once and get a type-safe, compile-checked, suspend-friendly API.

            Room is not a standalone feature; it sits inside the data layer behind a repository. The repository is the only class that touches the DAO, and it treats the cache as the single source of truth the UI ultimately reads. That keeps query details out of the ViewModel and lets the dev flavor swap a failing DAO in for testing without the rest of the app noticing.
        """.trimIndent(),
        demo = {
            Button(
                onClick = {
                    navController.navigate(NavRouter.PostsScreen.route)
                }) {
                Text("Posts example")
            }
        },
    )
}

@Composable
fun RoomOverview() {
    Section(
        title = "The three pieces of Room",
        description = """
            Room splits local persistence into three cooperating types:

            -Entity: a data class annotated @Entity that maps to a SQLite table. Fields become columns.
            -DAO (Data Access Object): an interface annotated @Dao whose methods are SQL queries. Room generates the implementation; methods are suspend so they run off the main thread.
            -Database: an abstract class annotated @Database that holds the DAOs and bootstraps the SQLite connection. It is a singleton scoped to the application.

            The same split appears in this app as PostEntity, PostDao, and AppDatabase.
        """.trimIndent(),
    )
}

@Composable
fun Entity() {
    Section(
        title = "Entity",
        description = """
            An entity is the on-disk shape of one row. It belongs to the data layer and is deliberately separate from the domain model — the domain Post and the data PostEntity describe the same concept but live in different layers, and a mapper translates between them. That separation is what lets you change the table schema without rippling into the UI.

            @PrimaryKey marks the unique row id; tableName sets the SQL table name. Keep entities free of business logic — they are storage, not behaviour.
        """.trimIndent(),
        code = """
            // data/local/entities/PostEntity.kt
            @Entity(tableName = "posts")
            data class PostEntity(
                @PrimaryKey val id: Int,
                val userId: Int,
                val title: String,
                val body: String
            )
        """.trimIndent()
    )
}

@Composable
fun Dao() {
    Section(
        title = "DAO",
        description = """
            A DAO is an interface of SQL. @Query takes a SQL string that Room validates at compile time (wrong column name = build error, not a runtime crash). @Insert / @Update / @Delete generate their SQL for you. Marking the functions suspend means they integrate naturally with coroutines and the repository's other suspend calls — no thread hopping, no AsyncTask, no runInBackground.

            OnConflictStrategy.REPLACE on the insert means re-fetching a post overwrites the cached row instead of throwing a UNIQUE violation, which is exactly what you want when the API is the authority and the cache is its mirror.
        """.trimIndent(),
        code = """
            // data/local/daos/PostDao.kt
            @Dao
            interface PostDao {
                @Query("SELECT * FROM posts")
                suspend fun getPosts(): List<PostEntity>

                @Query("SELECT * FROM posts WHERE id = :id")
                suspend fun getPostById(id: Int): PostEntity?

                @Query("SELECT * FROM posts WHERE userId = :userId")
                suspend fun getPostsByUserId(userId: Int): List<PostEntity>

                @Insert(onConflict = OnConflictStrategy.REPLACE)
                suspend fun insertPosts(posts: List<PostEntity>)
            }
        """.trimIndent()
    )
}

@Composable
fun AppDatabase() {
    Section(
        title = "AppDatabase",
        description = """
            The @Database class is the entry point to SQLite. It declares which entities belong in the schema and which version the schema is on, and exposes one abstract function per DAO. Room generates the implementation; you never write the body.

            The actual instance is built once by the DI layer with Room.databaseBuilder(...).build() and shared as a singleton, so the whole app reuses one SQLite connection instead of opening a new one per screen.
        """.trimIndent(),
        code = """
            // data/local/AppDatabase.kt
            @Database(
                entities = [
                    PostEntity::class,
                    // add other entities here
                ],
                version = 1
            )
            abstract class AppDatabase : RoomDatabase() {
                abstract fun postDao(): PostDao
            }
        """.trimIndent()
    )
}

@Composable
fun RepositoryIntegration() {
    Section(
        title = "Repository integration & single source of truth",
        description = """
            The DAO is never called from the UI. PostRepositoryImpl is the only consumer, and it coordinates remote and local: on a successful fetch it writes the remote posts into the cache, then returns the cache as the value the UI displays. On network/server failure it falls back to whatever is cached, carrying the original error as a non-blocking warning.

            Every database access is wrapped in guardedDatabaseCall so that an unexpected DB failure becomes a typed DatabaseException (and then a CacheFailure) rather than a crash. This is what "the cache is the single source of truth" looks like in code: one read path, one write path, both behind the repository.
        """.trimIndent(),
        code = """
            // data/repositoryimplementations/PostRepositoryImpl.kt (excerpt)
            return safeRepositoryCall(
                block = {
                    val remotePosts = guardedApiCall { api.getPosts() }
                    guardedDatabaseCall { dao.insertPosts(remotePosts.map { it.toDomain().toEntity() }) }
                    remotePosts.map { it.toDomain() }
                },
                onNetworkError = { fallbackToCache(it) },   // read the cache, attach the error as a warning
                onServerError = { fallbackToCache(it) }
            )
        """.trimIndent()
    )
}

@Composable
fun DependencyInjection() {
    Section(
        title = "Wiring it with Hilt",
        description = """
            Database wiring is split so the shared parts live in main and the parts that vary by flavor live per-flavor. DatabaseModule (in main) provides the AppDatabase singleton — every flavor uses the same real Room database. The PostDao binding lives in each flavor's FlavorNetworkModule instead: staging and prod return database.postDao(), while the dev flavor can wrap it in a FailingPostDao to simulate read/write failures. Same repository, same DAO interface, different concrete object per build.
        """.trimIndent(),
        code = """
            // di/DatabaseModule.kt — shared across flavors
            @Module
            @InstallIn(SingletonComponent::class)
            object DatabaseModule {
                @Provides @Singleton
                fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
                    Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME).build()
            }

            // di/FlavorNetworkModule.kt — per flavor
            @Provides @Singleton
            fun providePostDao(database: AppDatabase): PostDao = database.postDao()
        """.trimIndent()
    )
}

@Composable
fun Migrations() {
    Section(
        title = "Schema changes & migrations",
        description = """
            When an entity changes (a column added, a type changed) the schema version must be bumped on @Database and a Migration supplied to Room so existing users' data survives the upgrade. A Migration declares the from/to version and the SQL that transforms the old table into the new one; you pass migrations into Room.databaseBuilder via .addMigrations(...). Without one, Room will either throw (the default) or, if you opt into .fallbackToDestructiveMigration(), wipe the database on upgrade.

            This app currently ships version 1 with a single entity and therefore no migrations yet — but the integration point is the builder, and the discipline of bumping version on every schema change is what keeps existing users from losing data.
        """.trimIndent(),
        code = """
            val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE posts ADD COLUMN updatedAt INTEGER NOT NULL DEFAULT 0")
                }
            }

            Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME)
                .addMigrations(MIGRATION_1_2)
                .build()
        """.trimIndent()
    )
}

@Preview
@Composable
fun DatabaseHandlingScreenPreview() {
    DatabaseHandlingScreen(navController = rememberNavController())
}
