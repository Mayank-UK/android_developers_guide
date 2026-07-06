package com.androiddevelopersguide.data.local.daos

import com.androiddevelopersguide.core.exceptions.DatabaseException
import com.androiddevelopersguide.core.scenario.DbScenario
import com.androiddevelopersguide.data.local.entities.PostEntity

/**
 * Dev-only decorator that wraps the **real** Room [PostDao] and injects failures according to
 * [scenario]. Storage is fully delegated to [inner] via Kotlin interface delegation (`by inner`),
 * so this class contains *no* storage logic — only failure simulation. New methods added to
 * [PostDao] are auto-delegated with zero maintenance here; you only override the read/write
 * entry points where failure injection applies.
 *
 * Edit [scenario] below to pick a path, exactly like the iOS/Flutter guide fakes.
 */
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
