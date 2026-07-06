package com.androiddevelopersguide.data.remote

import com.androiddevelopersguide.core.exceptions.DecodingException
import com.androiddevelopersguide.core.exceptions.NetworkException
import com.androiddevelopersguide.core.exceptions.ServerException
import com.androiddevelopersguide.core.scenario.ApiScenario
import com.androiddevelopersguide.data.remote.dtos.PostDto
import kotlinx.coroutines.delay

/**
 * FakeApiService has two objectives:
 *   - Allow development to continue without a backend.
 *   - Simulate different failure modes so error-handling paths can be exercised.
 *
 * Change [scenario] (passed in at construction from FlavorNetworkModule) to pick which path the
 * next call takes — exactly like the Flutter and iOS guide projects' fakes.
 */
class FakeApiService(
    private val scenario: ApiScenario = ApiScenario.SUCCESS
//        private val scenario: ApiScenario = ApiScenario.NETWORK_ERROR
) : ApiService {

    private val fakePosts = listOf(
        PostDto(userId = 1, id = 1, title = "Fake Post 1", body = "Body of fake post 1"),
        PostDto(userId = 1, id = 2, title = "Fake Post 2", body = "Body of fake post 2"),
        PostDto(userId = 2, id = 3, title = "Fake Post 3", body = "Body of fake post 3")
    )

    private suspend fun <T> simulate(result: () -> T): T {
        delay(800) // simulate network latency
        return when (scenario) {
            ApiScenario.SUCCESS -> result()

            ApiScenario.NETWORK_ERROR ->
                throw NetworkException.noConnection(context = "Simulated network error")

            ApiScenario.TIMEOUT ->
                throw NetworkException.timeout(context = "Simulated request timed out")

            ApiScenario.SERVER_ERROR ->
                throw ServerException.internalServerError(context = "Simulated server returned 500")

            ApiScenario.INVALID_JSON ->
                throw DecodingException.jsonDecodingFailed(context = "Simulated invalid response format")
        }
    }

    override suspend fun getPosts(): List<PostDto> = simulate { fakePosts }

    override suspend fun getPostById(id: Int): PostDto = simulate {
        fakePosts.find { it.id == id }
            ?: throw ServerException.notFound(context = "Simulated post not found")
    }
}
