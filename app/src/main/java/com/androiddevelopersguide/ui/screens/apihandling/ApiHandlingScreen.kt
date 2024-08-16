package com.androiddevelopersguide.ui.screens.apihandling

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
fun ApiHandlingScreen(
    navController: NavController,
    viewModel: ApiHandlingViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Api handling",
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
            It is very important to follow a very clean and structured way to handle API calls.
            API services are usually integrated with the repository layer. Repository layer then handles both remote and local connections to optimize the user experience and network calls.
            
            In android, multiple libraries are used often to implement the API like Retrofit, Hilt, etc.
            
            There are many parts to API integration, these are consistent across different technologies like:
            
            -DTO's (Data Transfer Objects):
            These are used to store data from API request. These are then used to provide data to the respective models.
            
            -ApiService:
            This Service makes call to the endpoints.
            
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
            // ExampleDto.kt
            data class PostDto(
                @SerializedName("userId") val userId: Int,
                @SerializedName("id") val id: Int,
                @SerializedName("title") val title: String,
                @SerializedName("body") val body: String
            )
            
            // ApiService.kt example
            interface ApiService {
                @GET("posts")
                suspend fun getPosts(): List<PostDto>

                @GET("posts/{id}")
                suspend fun getPostById(@Path("id") id: Int): PostDto
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
            object NetworkModule {
                @Provides
                @Singleton
                fun provideRetrofit(): Retrofit {
                    return Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }

                @Provides
                @Singleton
                fun providePostApiService(retrofit: Retrofit): ApiService {
                    return retrofit.create(ApiService::class.java)
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
fun ApiHandlingScreenPreview() {
    ApiHandlingScreen(navController = rememberNavController())
}