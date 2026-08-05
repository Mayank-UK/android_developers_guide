package com.androiddevelopersguide.ui.screens.apihandling

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
fun ApiHandlingScreen(
    navController: NavController,
    viewModel: ApiHandlingViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Api handling",
        mobileUI = {
            Introduction(navController = navController)
            Retrofit()
            ApiService()
            Dto()
            OkHttpAndInterceptors()
            MappingToDomain()
            Guards()
            ErrorToFailure()
        }
    )
}

@Composable
fun Introduction(navController: NavController) {
    Section(
        title = "What & why",
        description = """
            Talking to a remote API is a layered job. The raw HTTP call is only the innermost piece; around it you need a stable contract (the endpoints), a wire format (the DTOs), a way to turn that format into domain models, and a boundary that turns network chaos (timeouts, 500s, malformed JSON) into typed values the UI can render. This app uses Retrofit for the contract, Gson for the wire format, OkHttp interceptors for cross-cutting HTTP behaviour, and a Result/Failure pair for the boundary — the same shape you would find in a production app.
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
fun Retrofit() {
    Section(
        title = "Retrofit",
        description = """
            Retrofit turns an annotated Kotlin interface into an HTTP client. You write one function per endpoint with a @GET / @POST / @PUT annotation giving the path; Retrofit builds the request, executes it, parses the response body into the return type you declared, and (with the suspend modifier) delivers it through a coroutine. The interface is the single source of truth for "what does the server offer" — no string URLs scattered through ViewModels.
        """.trimIndent(),
    )
}

@Composable
fun ApiService() {
    Section(
        title = "ApiService",
        description = """
            ApiService is the contract: one suspend function per endpoint, returning the DTO type the server responds with. @Path binds a path segment to a function parameter; Retrofit substitutes it into the URL. Keep this interface free of business logic and free of domain types — it should only describe the wire.
        """.trimIndent(),
        code = """
            // data/remote/ApiService.kt
            interface ApiService {
                @GET("posts")
                suspend fun getPosts(): List<PostDto>

                @GET("posts/{id}")
                suspend fun getPostById(@Path("id") id: Int): PostDto
            }
        """.trimIndent()
    )
}

@Composable
fun Dto() {
    Section(
        title = "DTO (Data Transfer Object)",
        description = """
            A DTO is the shape of the JSON on the wire, nothing more. @SerializedName maps a JSON key to a field so a rename on the server does not have to mean a rename in your code. DTOs live in the data layer and never escape it — they are translated into a domain model before crossing into the repository's return type. This keeps the domain honest about depending only on its own models, not on Retrofit or Gson annotations.
        """.trimIndent(),
        code = """
            // data/remote/dtos/PostDto.kt
            data class PostDto(
                @SerializedName("userId") val userId: Int,
                @SerializedName("id") val id: Int,
                @SerializedName("title") val title: String,
                @SerializedName("body") val body: String
            )
        """.trimIndent()
    )
}

@Composable
fun OkHttpAndInterceptors() {
    Section(
        title = "OkHttp & interceptors",
        description = """
            Retrofit is built on OkHttp, and OkHttp lets you slot interceptors into the call chain. An interceptor sees every request and response, so it is the right tool for cross-cutting HTTP concerns: auth headers, request/response logging, retries, and — in the dev flavor of this app — failure injection.

            FailureInjectionInterceptor sits in front of the real Retrofit client in dev builds and fabricates the same platform exceptions a genuinely failing network would throw (UnknownHostException, SocketTimeoutException, an HTTP 500 response, invalid JSON). That means the app's real error-handling path is exercised against the live API without a mock server, which is what makes the simulation trustworthy: prod and dev run the same recovery code.
        """.trimIndent(),
        code = """
            // app/src/dev/.../data/remote/FailureInjectionInterceptor.kt (excerpt)
            class FailureInjectionInterceptor(var scenario: ApiScenario) : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response = when (scenario) {
                    ApiScenario.SUCCESS      -> chain.proceed(chain.request())
                    ApiScenario.NETWORK_ERROR -> throw UnknownHostException("Simulated network error")
                    ApiScenario.TIMEOUT       -> throw SocketTimeoutException("Simulated timeout")
                    ApiScenario.SERVER_ERROR  -> buildResponse(chain.request(), code = 500)
                    ApiScenario.INVALID_JSON  -> buildResponse(chain.request(), code = 200, body = "{{invalid")
                }
            }

            // dev DI wires it in front of OkHttp
            OkHttpClient.Builder()
                .addInterceptor(failureInterceptor)
                .build()
        """.trimIndent()
    )
}

@Composable
fun MappingToDomain() {
    Section(
        title = "Mapping DTOs to domain models",
        description = """
            PostMapper is the only place that knows how DTOs, entities, and the domain Post relate. Centralising the conversion means a change to the wire format ripples in exactly one file. The mapper is an object of extension functions (PostDto.toDomain(), Post.toEntity()), which reads naturally at the call site and keeps the conversion logic stateless.
        """.trimIndent(),
        code = """
            // data/mappers/PostMapper.kt
            object PostMapper {
                fun PostDto.toDomain() = Post(id, userId, title, body)
                fun PostEntity.toDomain() = Post(id, userId, title, body)
                fun Post.toEntity() = PostEntity(id, userId, title, body)
            }

            // used at the repository boundary
            val remotePosts = guardedApiCall { api.getPosts() }     // List<PostDto>
            remotePosts.map { it.toDomain() }                       // List<Post>
        """.trimIndent()
    )
}

@Composable
fun Guards() {
    Section(
        title = "Guarded calls (GuardedApiCall / SafeRepositoryCall)",
        description = """
            Two free functions form the boundary between "things throw" and "things return Result". guardedApiCall wraps a single Retrofit call and converts platform exceptions (HttpException, SocketTimeoutException, UnknownHostException, JsonSyntaxException) into typed AppExceptions. safeRepositoryCall wraps a whole repository block: it runs the block, catches each typed AppException, and lets you supply a per-type recovery lambda (for example "on a network error, read the cache"); if you don't supply one, the exception is mapped to a Failure.

            The point is that no caller outside the data layer ever writes try/catch around an API call. The result is either a value or a Failure, period.
        """.trimIndent(),
        code = """
            // core/guards/GuardedApiCall.kt — platform exception -> AppException
            suspend fun <T> guardedApiCall(block: suspend () -> T): T = try {
                block()
            } catch (e: HttpException)            { throw mapHttpException(e) }
              catch (e: SocketTimeoutException)    { throw NetworkException.timeout(cause = e) }
              catch (e: UnknownHostException)      { throw NetworkException.noConnection(cause = e) }
              catch (e: JsonSyntaxException)       { throw DecodingException.jsonDecodingFailed(cause = e) }
              catch (e: IOException)               { throw NetworkException("Network error", cause = e) }

            // core/guards/SafeRepositoryCall.kt — AppException -> Result<T>
            suspend fun <T> safeRepositoryCall(
                block: suspend () -> T,
                onNetworkError: (suspend (NetworkException) -> Result<T>)? = null,
                onServerError:   (suspend (ServerException)   -> Result<T>)? = null,
                /* ...per-type callbacks... */
            ): Result<T>
        """.trimIndent()
    )
}

@Composable
fun ErrorToFailure() {
    Section(
        title = "From exception to Failure (ExceptionMapper)",
        description = """
            ExceptionMapper is the single point where a technical error becomes a user-facing one. It takes a typed AppException and returns a Failure (NetworkFailure, ServerFailure with its HTTP status code, or CacheFailure), choosing a severity at the same time: 5xx is CRITICAL, 4xx and network errors are WARNING, cache/database issues are INFO. Because every error flows through this one object, the wording and tone of error messages are consistent across the whole app, and changing a message is a one-line edit.
        """.trimIndent(),
        code = """
            // core/mapping/ExceptionMapper.kt — decides message + severity in one place
            is ServerException -> ServerFailure(
                message    = mapServerMessage(exception.message, exception.statusCode),
                severity   = if (statusCode in 500..599) CRITICAL else WARNING,
                statusCode = exception.statusCode,
                cause      = innerCause
            )
            is NetworkException -> NetworkFailure(message, WARNING, cause)
            is DatabaseException -> CacheFailure(message, INFO, cause)
        """.trimIndent()
    )
}

@Preview
@Composable
fun ApiHandlingScreenPreview() {
    ApiHandlingScreen(navController = rememberNavController())
}
