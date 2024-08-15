package com.androiddevelopersguide.contentproviders.userprovider

import android.net.Uri
import android.provider.BaseColumns

object UserContract {
    const val AUTHORITY = "com.androiddevelopersguide.provider"
    const val PATH_USERS = "users"
    val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH_USERS")

    object UserEntry : BaseColumns {
        const val TABLE_NAME = "users"
        const val COLUMN_USERNAME = "username"
    }
}