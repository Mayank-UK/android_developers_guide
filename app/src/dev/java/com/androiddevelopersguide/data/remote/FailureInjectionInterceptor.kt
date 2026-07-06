package com.androiddevelopersguide.data.remote

import com.androiddevelopersguide.core.scenario.ApiScenario
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Dev-only OkHttp [Interceptor] that injects API failures on the client side, in front of the
 * **real** Retrofit client. It throws or fabricates the same platform exceptions a real failing
 * network would, so the app's [com.androiddevelopersguide.core.guards.guardedApiCall] maps them
 * exactly as it would in production — no fake service required.
 *
 * Edit [scenario] below to pick a path (success / network error / timeout / server error /
 * invalid JSON). Because it is a `var`, it can also be flipped at runtime from a debug menu.
 *
 * Mirrors the failure simulation the FakeApiService used to do, but against the live API and
 * covering every endpoint automatically with no per-endpoint maintenance.
 */
class FailureInjectionInterceptor(
    var scenario: ApiScenario = ApiScenario.SUCCESS
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return when (scenario) {
            ApiScenario.SUCCESS -> chain.proceed(request)

            ApiScenario.NETWORK_ERROR ->
                throw UnknownHostException("Simulated network error (no connection)")

            ApiScenario.TIMEOUT ->
                throw SocketTimeoutException("Simulated request timeout")

            ApiScenario.SERVER_ERROR ->
                buildResponse(request, code = 500, body = "Simulated server error")

            ApiScenario.INVALID_JSON ->
                buildResponse(request, code = 200, body = "{{invalid-json", contentType = "application/json")
        }
    }

    private fun buildResponse(
        request: Request,
        code: Int,
        body: String,
        contentType: String = "text/plain"
    ): Response = Response.Builder()
        .request(request)
        .protocol(Protocol.HTTP_1_1)
        .code(code)
        .message("Simulated")
        .body(body.toResponseBody(contentType.toMediaType()))
        .build()
}
