package com.androiddevelopersguide.ui.screens.architecture

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun ArchitectureScreen(
    navController: NavController,
    viewModel: ArchitectureViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Architecture",
        mobileUI = {
            Introduction()
            LayersAndTheDependencyRule()
            ThisAppStructure()
            DomainLayer()
            DataLayer()
            CoreLayer()
            DependencyInjection()
            ErrorHandlingAndResult()
            WhyThisLayering()
        }
    )
}

@Composable
fun Introduction() {
    Section(
        title = "What & why",
        description = """
            Architecture is how you split a codebase into pieces that can each be reasoned about, tested, and changed without dragging the rest of the app along. On Android the default recommendation is a Clean-Architecture-flavoured MVVM: the UI observes state from a ViewModel, the ViewModel talks to use cases, use cases talk to repositories, and repositories coordinate remote and local data sources.

            The point is not ceremony. It is to make the rule that keeps the app maintainable explicit: dependencies point in one direction (UI → domain ← data), the inner layers never know how the outer layers are built, and failures cross layer boundaries as values, not as thrown exceptions. This app is structured that way, so every concept below is shown against its real package.
        """.trimIndent(),
    )
}

@Composable
fun LayersAndTheDependencyRule() {
    Section(
        title = "Layers & the dependency rule",
        description = """
            Three layers carry the bulk of the code:

            -UI layer: composables, ViewModels, navigation, theme. Owns nothing about where data comes from.
            -Domain layer: plain Kotlin models, repository interfaces, and use cases. No Android imports, no Retrofit, no Room.
            -Data layer: the implementations of those repository interfaces, plus the remote (Retrofit) and local (Room) sources they coordinate.

            The dependency rule: source-code dependencies may only point inward. The domain layer depends on nothing outer; the data layer implements the domain's interfaces but the domain never imports the data layer. That is what lets you swap the network, the database, or the whole data layer without the UI or domain noticing.
        """.trimIndent(),
        code = """
            // domain defines the contract — no Android, no Retrofit, no Room.
            interface PostRepository {
                suspend fun getPosts(): Result<List<Post>>
                suspend fun getPostById(id: Int): Result<Post>
            }

            // data implements it. Only this side knows about Retrofit and Room.
            class PostRepositoryImpl @Inject constructor(
                private val api: ApiService,
                private val dao: PostDao
            ) : PostRepository {
                override suspend fun getPosts(): Result<List<Post>> { /* ... */ }
            }

            // UI never sees PostRepositoryImpl. It only sees the use case the VM injects.
            @HiltViewModel
            class PostsViewModel @Inject constructor(
                private val getPostsUseCase: GetPostsUseCase
            ) : ViewModel()
        """.trimIndent()
    )
}

@Composable
fun ThisAppStructure() {
    Section(
        title = "This app's package structure",
        description = """
            The package layout mirrors the layers above. Reading it top to bottom tells you how a request flows: a screen calls a use case, the use case calls a repository interface, the implementation in data/ fetches from remote and caches in local, and core/ holds the cross-cutting machinery (Result, Failure, guards, mappers) that the data layer relies on.
        """.trimIndent(),
        code = """
            com/androiddevelopersguide/
                core/                       cross-cutting, no UI
                    exceptions/             AppException + typed subtypes
                    failures/               Failure, FailureSeverity, NetworkFailure, ...
                    guards/                 guardedApiCall, guardedDatabaseCall, safeRepositoryCall
                    mapping/                ExceptionMapper (exception -> failure)
                    result/                 Result<T> sealed class
                    scenario/               ApiScenario, DbScenario (dev simulation)
                domain/                     plain Kotlin, depends on nothing outer
                    models/                 Post, AppTheme, LinkData
                    repositoryinterfaces/   PostRepository, ThemeRepository
                    usecases/               GetPostsUseCase, GetPostDetailUseCase, ThemeUseCases
                data/                       implements the domain interfaces
                    local/                  AppDatabase, daos/PostDao, entities/PostEntity
                    mappers/                PostMapper (DTO <-> domain <-> entity)
                    remote/                 ApiService, dtos/PostDto
                    repositoryimplementations/   PostRepositoryImpl, ThemeRepositoryImpl
                di/                         Hilt modules (Database, Repository, DataStore, flavor)
                ui/                         screens, components, navigation, theme
        """.trimIndent()
    )
}

@Composable
fun DomainLayer() {
    Section(
        title = "Domain layer",
        description = """
            The domain layer is the part of the app that describes what the app does, not how it talks to the outside world. It contains pure data classes (Post, AppTheme), repository interfaces (PostRepository), and use cases (GetPostsUseCase, GetPostDetailUseCase) that each wrap a single repository operation.

            Because it has no Android or framework imports, domain code is fast to unit-test and stable across UI/tooling changes. A use case is intentionally thin here — it delegates to a repository — but it is the correct place to put business rules (filtering, combining multiple repositories, validation) so that logic stays out of the ViewModel and out of the data layer.
        """.trimIndent(),
        code = """
            // domain/models/Post.kt — pure data, no annotations
            data class Post(
                val userId: Int,
                val id: Int,
                val title: String,
                val body: String
            )

            // domain/usecases/GetPostsUseCase.kt
            class GetPostsUseCase @Inject constructor(
                private val repository: PostRepository
            ) {
                suspend operator fun invoke(): Result<List<Post>> = repository.getPosts()
            }
        """.trimIndent()
    )
}

@Composable
fun DataLayer() {
    Section(
        title = "Data layer",
        description = """
            The data layer is where the domain's abstractions become Retrofit calls and Room queries. It owns three responsibilities: define the wire format (DTOs), define the on-disk format (entities), and implement each repository interface by coordinating remote and local sources. Mapping between DTO, entity, and domain model is centralised in a single mapper object so the conversion rules live in exactly one place.

            PostRepositoryImpl.getPosts is a good example of the layer's job: it calls the API, writes the result into the cache, and returns the cache as the single source of truth. On network/server failure it falls back to whatever is cached, carrying the original error as a non-blocking warning so the UI can show stale data with a banner instead of an empty error screen.
        """.trimIndent(),
        code = """
            // data/mappers/PostMapper.kt — the only place these conversions exist
            object PostMapper {
                fun PostDto.toDomain() = Post(id, userId, title, body)
                fun PostEntity.toDomain() = Post(id, userId, title, body)
                fun Post.toEntity() = PostEntity(id, userId, title, body)
            }

            // data/repositoryimplementations/PostRepositoryImpl.kt — single source of truth
            override suspend fun getPosts(): Result<List<Post>> = safeRepositoryCall(
                block = {
                    val remote = guardedApiCall { api.getPosts() }
                    guardedDatabaseCall { dao.insertPosts(remote.map { it.toDomain().toEntity() }) }
                    remote.map { it.toDomain() }
                },
                onNetworkError = { fallbackToCache(it) },
                onServerError = { fallbackToCache(it) }
            )
        """.trimIndent()
    )
}

@Composable
fun CoreLayer() {
    Section(
        title = "Core layer (Result, Failure, guards)",
        description = """
            Core holds the cross-cutting types that both domain and data build on, but it stays free of UI. The two ideas that hold the error story together are:

            -Result<T>: a sealed type returned by every repository method. Success carries a value (and an optional non-blocking warning Failure), Failed carries a Failure. Because it is a value, the UI never needs try/catch — it pattern-matches.
            -Failure: the domain/UI's representation of an error, with a user-facing message, a severity (INFO / WARNING / CRITICAL), and an optional cause chain. Subtypes include NetworkFailure, ServerFailure (which carries an HTTP status code), and CacheFailure.

            The data layer never throws raw platform exceptions across the boundary. guardedApiCall and guardedDatabaseCall translate Retrofit/OkHttp/Room failures into typed AppExceptions, and ExceptionMapper converts those AppExceptions into Failures with appropriate severities and messages.
        """.trimIndent(),
        code = """
            sealed class Result<out T> {
                data class Success<out T>(val value: T, val warning: Failure? = null) : Result<T>()
                data class Failed<out T>(val failure: Failure) : Result<T>()

                fun <U> map(transform: (T) -> U): Result<U>          // transform success
                fun <U> flatMap(transform: (T) -> Result<U>): Result<U>   // chain
                fun recover(transform: (Failure) -> Result<T>): Result<T> // fallback
            }

            enum class FailureSeverity { INFO, WARNING, CRITICAL }

            sealed class Failure {
                abstract val message: String
                abstract val severity: FailureSeverity
                abstract val cause: Failure?
            }
        """.trimIndent()
    )
}

@Composable
fun DependencyInjection() {
    Section(
        title = "Dependency injection (Hilt)",
        description = """
            Hilt wires the layers together at construction time so no class instantiates its own dependencies. ViewModels are annotated @HiltViewModel and receive use cases through their constructor; repository implementations receive their ApiService and DAO the same way. Hilt modules in di/ declare how to build the singleton graph: DatabaseModule provides the Room database, RepositoryModule binds each interface to its implementation, and a per-flavor FlavorNetworkModule provides the Retrofit client and ApiService.

            The flavor split is deliberate: the dev flavor can layer a FailureInjectionInterceptor in front of OkHttp and swap in a FailingPostDao, while staging and prod build the plain real client — same interface, different wiring, decided entirely by which source set compiled.
        """.trimIndent(),
        code = """
            @Module
            @InstallIn(SingletonComponent::class)
            object RepositoryModule {
                @Provides @Singleton
                fun providePostRepository(
                    api: ApiService,
                    dao: PostDao
                ): PostRepository = PostRepositoryImpl(api, dao)
            }

            @HiltViewModel
            class PostsViewModel @Inject constructor(
                private val getPostsUseCase: GetPostsUseCase   // depends on the interface, not the impl
            ) : ViewModel()
        """.trimIndent()
    )
}

@Composable
fun ErrorHandlingAndResult() {
    Section(
        title = "How errors cross the boundary",
        description = """
            Two guards do the heavy lifting so the rest of the app never sees a try/catch. guardedApiCall wraps a Retrofit call and converts platform exceptions (HttpException, SocketTimeoutException, UnknownHostException, JsonSyntaxException) into typed AppExceptions. safeRepositoryCall wraps a repository block: it catches those AppExceptions and lets the repository supply per-type recovery (for example, "on a network error, read the cache"), falling back to ExceptionMapper to produce a Failure when no recovery applies.

            ExceptionMapper is the single place where technical messages become user-facing ones and where severity is decided: 5xx becomes CRITICAL, 4xx and network errors become WARNING, and cache/database issues become INFO. Change the wording of an error message here and it changes everywhere.
        """.trimIndent(),
        code = """
            // Two-stage boundary: exceptions in, Result out.
            override suspend fun getPostById(id: Int): Result<Post> = safeRepositoryCall(
                block = {
                    try {
                        guardedApiCall { api.getPostById(id) }.toDomain()
                    } catch (apiError: AppException) {
                        val cached = guardedDatabaseCall { dao.getPostById(id) }
                        cached?.toDomain() ?: throw apiError
                    }
                }
            )
        """.trimIndent()
    )
}

@Composable
fun WhyThisLayering() {
    Section(
        title = "Why this layering pays off",
        description = """
            The cost of the layering is a few small classes (one use case per operation, one mapper, a Result type). What it buys:

            -Testability: ViewModels and use cases are unit-testable with a fake repository because they depend on an interface. The data layer is testable with MockK because guardedApiCall/guardedDatabaseCall isolate the platform types.
            -Swappable sources: the dev flavor proves this by swapping the real API/DAO for fakes without touching domain or UI code.
            -Single source of truth: the cache is the source the UI reads; remote writes into it. The UI never reads remote and local separately and tries to reconcile.
            -Consistent errors: every failure arrives at the UI as a typed Failure with a severity, instead of a generic Exception string the UI has to interpret.
        """.trimIndent(),
    )
}

@Preview
@Composable
fun ArchitectureScreenPreview() {
    ArchitectureScreen(navController = rememberNavController())
}
