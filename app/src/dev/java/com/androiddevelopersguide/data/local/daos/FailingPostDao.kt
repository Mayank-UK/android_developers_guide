package com.androiddevelopersguide.data.local.daos

import com.androiddevelopersguide.core.exceptions.DatabaseException
import com.androiddevelopersguide.core.scenario.DbScenario
import com.androiddevelopersguide.data.local.entities.PostEntity

/**
 * REFERENCE / SHOWCASE — not wired into the dev DI.
 *
 * A one-off decorator used to manually verify the Post DB error-handling paths. It is intentionally
 * NOT maintained per feature: real SQLite/Room has no chokepoint for clean failure injection (unlike
 * the API, which has the OkHttp [com.androiddevelopersguide.data.remote.FailureInjectionInterceptor]),
 * so DB error paths are verified via MockK unit tests, not by wiring this into the app.
 *
 * To use it for a manual check, temporarily wrap the DAO in `DatabaseModule.providePostDao`:
 * ```
 * return FailingPostDao(inner = database.postDao(), scenario = DbScenario.READ_ERROR)
 * ```
 * then flip the `scenario` back to SUCCESS (or unwire it) when done.
 */
// Just make it for a single feature to test the DB functionality at least once, do not maintain it for all the features

class FailingPostDao(
    private val inner: PostDao,
    private val scenario: DbScenario = DbScenario.SUCCESS
//        private val scenario: DbScenario = DbScenario.READ_ERROR
) : PostDao by inner {

    private fun checkRead() {
        if (scenario == DbScenario.READ_ERROR) {
            throw DatabaseException.readFailed(context = "Simulated DB read error")
        }
    }

    private fun checkWrite() {
        if (scenario == DbScenario.WRITE_ERROR) {
            throw DatabaseException.writeFailed(context = "Simulated DB write error")
        }
    }

    override suspend fun getPosts(): List<PostEntity> {
        checkRead()
        val result = inner.getPosts()
        return if (scenario == DbScenario.EMPTY) emptyList() else result
    }

    override suspend fun getPostById(id: Int): PostEntity? {
        checkRead()
        return inner.getPostById(id)
    }

    override suspend fun getPostsByUserId(userId: Int): List<PostEntity> {
        checkRead()
        return inner.getPostsByUserId(userId)
    }

    override suspend fun insertPosts(posts: List<PostEntity>) {
        checkWrite()
        inner.insertPosts(posts)
    }
}
