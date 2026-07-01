package com.androiddevelopersguide.data.mappers

import com.androiddevelopersguide.data.local.entities.PostEntity
import com.androiddevelopersguide.data.mappers.PostMapper.toDomain
import com.androiddevelopersguide.data.mappers.PostMapper.toEntity
import com.androiddevelopersguide.data.remote.dtos.PostDto
import com.androiddevelopersguide.domain.models.Post
import org.junit.Assert.assertEquals
import org.junit.Test

class PostMapperTest {

    @Test
    fun `PostDto to Domain Post`() {
        val dto = PostDto(userId = 1, id = 10, title = "Title", body = "Body")
        val domain = dto.toDomain()

        assertEquals(dto.id, domain.id)
        assertEquals(dto.userId, domain.userId)
        assertEquals(dto.title, domain.title)
        assertEquals(dto.body, domain.body)
    }

    @Test
    fun `PostEntity to Domain Post`() {
        val entity = PostEntity(userId = 1, id = 10, title = "Title", body = "Body")
        val domain = entity.toDomain()

        assertEquals(entity.id, domain.id)
        assertEquals(entity.userId, domain.userId)
        assertEquals(entity.title, domain.title)
        assertEquals(entity.body, domain.body)
    }

    @Test
    fun `Domain Post to PostEntity`() {
        val domain = Post(userId = 1, id = 10, title = "Title", body = "Body")
        val entity = domain.toEntity()

        assertEquals(domain.id, entity.id)
        assertEquals(domain.userId, entity.userId)
        assertEquals(domain.title, entity.title)
        assertEquals(domain.body, entity.body)
    }
}
