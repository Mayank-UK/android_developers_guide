package com.androiddevelopersguide.contentproviders.userprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.net.Uri
import android.provider.BaseColumns
import android.util.Log

class UserProvider : ContentProvider() {
    private lateinit var dbHelper: UserDbHelper

    companion object {
        private const val USERS = 1
        private const val USER_ID = 2
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(UserContract.AUTHORITY, UserContract.PATH_USERS, USERS)
            addURI(UserContract.AUTHORITY, "${UserContract.PATH_USERS}/#", USER_ID)
        }
        private const val TAG = "UserProvider"
    }

    override fun onCreate(): Boolean {
        return try {
            dbHelper = UserDbHelper(context ?: throw IllegalStateException("Context is null"))
            true
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing UserProvider", e)
            false
        }
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return try {
            val db = dbHelper.readableDatabase
            val cursor = when (uriMatcher.match(uri)) {
                USERS -> db.query(
                    UserContract.UserEntry.TABLE_NAME, projection, selection,
                    selectionArgs, null, null, sortOrder)
                USER_ID -> {
                    val id = uri.lastPathSegment
                    db.query(
                        UserContract.UserEntry.TABLE_NAME, projection, "${BaseColumns._ID} = ?",
                        arrayOf(id), null, null, sortOrder)
                }
                else -> throw IllegalArgumentException("Unknown URI: $uri")
            }
            cursor.setNotificationUri(context?.contentResolver ?: throw IllegalStateException("ContentResolver is null"), uri)
            cursor
        } catch (e: Exception) {
            Log.e(TAG, "Error querying database", e)
            null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return try {
            if (uriMatcher.match(uri) != USERS) {
                throw IllegalArgumentException("Invalid URI for insertion")
            }
            val db = dbHelper.writableDatabase
            val id = db.insert(UserContract.UserEntry.TABLE_NAME, null, values)
            if (id != -1L) {
                context?.contentResolver?.notifyChange(uri, null)
                ContentUris.withAppendedId(uri, id)
            } else {
                throw SQLException("Failed to insert row into $uri")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error inserting into database", e)
            null
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        return try {
            val db = dbHelper.writableDatabase
            val count = when (uriMatcher.match(uri)) {
                USERS -> db.update(UserContract.UserEntry.TABLE_NAME, values, selection, selectionArgs)
                USER_ID -> {
                    val id = uri.lastPathSegment
                    db.update(
                        UserContract.UserEntry.TABLE_NAME, values, "${BaseColumns._ID} = ?",
                        arrayOf(id))
                }
                else -> throw IllegalArgumentException("Unknown URI: $uri")
            }
            if (count > 0) {
                context?.contentResolver?.notifyChange(uri, null)
            }
            count
        } catch (e: Exception) {
            Log.e(TAG, "Error updating database", e)
            0
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return try {
            val db = dbHelper.writableDatabase
            val count = when (uriMatcher.match(uri)) {
                USERS -> db.delete(UserContract.UserEntry.TABLE_NAME, selection, selectionArgs)
                USER_ID -> {
                    val id = uri.lastPathSegment
                    db.delete(UserContract.UserEntry.TABLE_NAME, "${BaseColumns._ID} = ?", arrayOf(id))
                }
                else -> throw IllegalArgumentException("Unknown URI: $uri")
            }
            if (count > 0) {
                context?.contentResolver?.notifyChange(uri, null)
            }
            count
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting from database", e)
            0
        }
    }

    override fun getType(uri: Uri): String? {
        return try {
            when (uriMatcher.match(uri)) {
                USERS -> "vnd.android.cursor.dir/${UserContract.AUTHORITY}.${UserContract.PATH_USERS}"
                USER_ID -> "vnd.android.cursor.item/${UserContract.AUTHORITY}.${UserContract.PATH_USERS}"
                else -> throw IllegalArgumentException("Unknown URI: $uri")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error getting type for URI: $uri", e)
            null
        }
    }
}