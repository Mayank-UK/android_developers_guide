package com.androiddevelopersguide.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androiddevelopersguide.data.local.daos.PostDao
import com.androiddevelopersguide.data.local.entities.PostEntity

@Database(entities = [
    PostEntity::class,
    // Add other entities here
                     ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}