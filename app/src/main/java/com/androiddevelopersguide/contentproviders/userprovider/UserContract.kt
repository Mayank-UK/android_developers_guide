package com.androiddevelopersguide.contentproviders.userprovider

import android.net.Uri
import android.provider.BaseColumns

import com.androiddevelopersguide.BuildConfig

object UserContract {
    val AUTHORITY = BuildConfig.contentProviderAuthority
    const val PATH_USERS = "users"
    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH_USERS")

    object UserEntry : BaseColumns {
        const val TABLE_NAME = "users"
        const val COLUMN_USERNAME = "username"
    }
}