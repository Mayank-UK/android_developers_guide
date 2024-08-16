package com.androiddevelopersguide.ui.screens.databasehandling

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section
import com.androiddevelopersguide.ui.navigation.NavRouter

@Composable
fun DatabaseHandlingScreen(
    navController: NavController,
    viewModel: DatabaseHandlingViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Database handling",
            )
        },
        mobileUI = {
            Basics(navController = navController)
        }
    )
}

@Composable
fun Basics(navController: NavController) {
    Section(
        title = "Basics",
        description = """
            It is very important to follow a very clean and structured way to handle database locally.
            Database services are usually integrated with the repository layer. Repository layer then handles both remote and local connections to optimize the user experience and network calls.
            
            In android, multiple libraries are used often to implement the local database in android like Room, etc.
            
            There are many parts to local database integration, these are consistent across different technologies like:
            
            -Entities
            These are used to define tables in the database.
            
            -DAO's (Database Access Objects):
            These are used to access database through objects.
            
            -Repository:
            This repository is a mechanism to handle data from local and remote sources. This helps in optimizing network calls and providing a single point to request data in the application.
            
            -UseCases:
            These represents the actions that can be performed on the repository.
        """.trimIndent(),
        demo = {
            Button(
                onClick = {
                    navController.navigate(NavRouter.PostsScreen.route)
                }) {
                Text("Posts example")
            }
        },
        code = """
            // ExampleEntity.kt
            @Entity(tableName = "posts")
            data class PostEntity(
                @PrimaryKey val id: Int,
                val userId: Int,
                val title: String,
                val body: String
            )
            
            // ExampleDao.kt
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
            
            // ExampleDatabase.kt
            @Database(entities = [
                PostEntity::class,
                // Add other entities here
                                 ], version = 1)
            abstract class AppDatabase : RoomDatabase() {
                abstract fun postDao(): PostDao
            }
            
            // ExampleRepositoryInterface.kt
            interface PostRepository {
                suspend fun getPosts(): List<Post>
                suspend fun getPostById(id: Int): Post
            }
            
            // ExampleRepositoryImplementation.kt
            class PostRepositoryImpl @Inject constructor(
                private val api: ApiService,
                private val dao: PostDao
            ) : PostRepository {

                override suspend fun getPosts(): List<Post> {
                    return try {
                        val remoteUsers = api.getPosts()
                        dao.insertPosts(remoteUsers.map { it.toDomain().toEntity() })
                        dao.getPosts().map { it.toDomain() }
                    } catch (e: Exception) {
                        dao.getPosts().map { it.toDomain() }
                    }
                }

                override suspend fun getPostById(id: Int): Post {
                    return try {
                        api.getPostById(id).toDomain()
                    } catch (e: Exception) {
                        dao.getPostById(id)?.toDomain()
                    }!!
                }
            }
            
            // dependency injection
            @Module
            @InstallIn(SingletonComponent::class)
            object DatabaseModule {
                @Provides
                @Singleton
                fun providePostDatabase(@ApplicationContext context: Context): AppDatabase {
                    return Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        APP_DATABASE_NAME
                    ).build()
                }

                @Provides
                fun providePostDao(database: AppDatabase): PostDao {
                    return database.postDao()
                }
            }
            
            @Module
            @InstallIn(SingletonComponent::class)
            object RepositoryModule {
                @Provides
                @Singleton
                fun providePostRepository(
                    api: ApiService,
                    dao: PostDao
                ): PostRepository {
                    return PostRepositoryImpl(api, dao)
                }
            }
            
            // ExampleUseCase.kt
            class GetPostDetailUseCase @Inject constructor(private val repository: PostRepository) {
                suspend operator fun invoke(id: Int): Post = repository.getPostById(id)
            }
            
            // ExampleViewModel.kt
            @HiltViewModel
            class PostsViewModel @Inject constructor(
                private val getPostsUseCase: GetPostsUseCase
            ) : ViewModel() {

                // this is a common pattern to have two variables
                private val _posts = MutableStateFlow<List<Post>>(emptyList())
                val posts: StateFlow<List<Post>> = _posts.asStateFlow()

                private val _isLoading = MutableStateFlow(true)
                val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

                init {
                    loadPosts()
                }

                private fun loadPosts() {
                    viewModelScope.launch {
                        _isLoading.value = true
                        _posts.value = getPostsUseCase()
                        _isLoading.value = false
                    }
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun DatabaseHandlingScreenPreview() {
    DatabaseHandlingScreen(navController = rememberNavController())
}