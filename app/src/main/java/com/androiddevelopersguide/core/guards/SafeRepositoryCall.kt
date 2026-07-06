package com.androiddevelopersguide.core.guards

import com.androiddevelopersguide.core.exceptions.AppException
import com.androiddevelopersguide.core.exceptions.DatabaseException
import com.androiddevelopersguide.core.exceptions.DecodingException
import com.androiddevelopersguide.core.exceptions.NetworkException
import com.androiddevelopersguide.core.exceptions.ServerException
import com.androiddevelopersguide.core.mapping.ExceptionMapper
import com.androiddevelopersguide.core.result.Result

/**
 * Repository-layer boundary: runs [block] and converts any thrown [AppException] into a
 * [Result.Failed], so the domain/UI layers only ever see a [Result].
 *
 * Optional typed callbacks ([onNetworkError], [onServerError], [onDatabaseError],
 * [onDecodingError]) let a repository implement recovery per error type — e.g. fall back to the
 * local cache on a network/server error. When a callback is omitted, the exception is mapped to
 * the default [com.androiddevelopersguide.core.failures.Failure] via [ExceptionMapper].
 */
suspend fun <T> safeRepositoryCall(
    block: suspend () -> T,
    onNetworkError: (suspend (NetworkException) -> Result<T>)? = null,
    onServerError: (suspend (ServerException) -> Result<T>)? = null,
    onDatabaseError: (suspend (DatabaseException) -> Result<T>)? = null,
    onDecodingError: (suspend (DecodingException) -> Result<T>)? = null
): Result<T> = try {
    Result.Success(block())
} catch (e: NetworkException) {
    onNetworkError?.invoke(e) ?: Result.Failed(ExceptionMapper.map(e))
} catch (e: ServerException) {
    onServerError?.invoke(e) ?: Result.Failed(ExceptionMapper.map(e))
} catch (e: DatabaseException) {
    onDatabaseError?.invoke(e) ?: Result.Failed(ExceptionMapper.map(e))
} catch (e: DecodingException) {
    onDecodingError?.invoke(e) ?: Result.Failed(ExceptionMapper.map(e))
} catch (e: AppException) {
    Result.Failed(ExceptionMapper.map(e))
} catch (e: Exception) {
    Result.Failed(ExceptionMapper.map(ServerException("Unexpected error", cause = e)))
}
