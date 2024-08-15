package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.contentprovider

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.contentproviders.userprovider.UserContract
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun ContentProviderScreen(
    navController: NavController,
    viewModel: ContentProviderViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Content provider",
            )
        },
        mobileUI = {
            Basics()
        }
    )
}

@Composable
fun Basics() {
    Section(
        title = "Basics",
        description = """
            A Content Provider is a component in Android that provides a structured storage mechanism for storing and managing app data. It allows different apps to share data with each other, and it provides a way for an app to expose its data to other apps.
            
            Types of Content Providers:

            -File-based Content Providers: 
            Store data in files, such as images or documents.
            
            -Database-based Content Providers: 
            Store data in a database, such as SQLite.
            
            -Network-based Content Providers: 
            Store data on a remote server, such as a web service.
            
            Features of content storage:

            -Data Storage: 
            Content Providers store data in a structured format, such as a database or a file.
            
            -Data Sharing: 
            Content Providers allow different apps to share data with each other.
            
            -Data Exposure: 
            Content Providers expose data to other apps, allowing them to access and manipulate the data.
            
            -Querying: 
            Content Providers provide a way to query the data using a URI-based syntax.
            
            -CRUD Operations: 
            Content Providers support Create, Read, Update, and Delete (CRUD) operations on the data.
            
            The difference between ContentProvider vs Room(or any other like this) is that ContentProvider can be used to share data across multiple apps, while Room is used to share data within a single app. API and Room database is accessed through a repository, while content provider is used separately.
            
            Note: The created contentProvider needs to be registered in the manifest file.
        """.trimIndent(),
        demo = {
            val context = LocalContext.current
            var name by remember { mutableStateOf("Mayank") }

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Enter Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = {
                        try {
                            val values = ContentValues().apply {
                                put(UserContract.UserEntry.COLUMN_USERNAME, name)
                            }
                            val newUri = context.contentResolver.insert(UserContract.CONTENT_URI, values)
                            if (newUri != null) {
                                Log.d("UserProvider", "Inserted new user with URI: $newUri")
                                Toast.makeText(context, "Inserted new user", Toast.LENGTH_SHORT).show()
                            } else {
                                Log.e("UserProvider", "Failed to insert user")
                                Toast.makeText(context, "Failed to insert user", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            Log.e("UserProvider", "Error inserting user", e)
                        }
                    }
                ) {
                    Text("Insert Username")
                }

                Button(
                    onClick = {
                        try {
                            val cursor = context.contentResolver.query(UserContract.CONTENT_URI, null, null, null, null)
                            cursor?.use {
                                val usernames = mutableListOf<String>()
                                while (it.moveToNext()) {
                                    val username = it.getString(it.getColumnIndexOrThrow(
                                        UserContract.UserEntry.COLUMN_USERNAME))
                                    usernames.add(username)
                                }
                                Toast.makeText(context, "Retrieved usernames: ${usernames.joinToString()}", Toast.LENGTH_SHORT).show()
                                Log.d("UserProvider", "Retrieved usernames: ${usernames.joinToString()}")
                            } ?: Log.e("UserProvider", "Query returned null cursor")
                        } catch (e: Exception) {
                            Log.e("UserProvider", "Error retrieving usernames", e)
                        }
                    }
                ) {
                    Text("Retrieve Usernames")
                }

                Button(onClick = {
                    val rowsDeleted = context.contentResolver.delete(
                        UserContract.CONTENT_URI,
                        null,
                        null
                    )
                    Toast.makeText(context, "Deleted $rowsDeleted users", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Clear Usernames")
                }
            }
        },
        code = """
            // UserContract.kt
            object UserContract {
                const val AUTHORITY = "com.androiddevelopersguide.provider"
                const val PATH_USERS = "users"
                val CONTENT_URI: Uri = Uri.parse("content://$/AUTHORITY/$/PATH_USERS")

                object UserEntry : BaseColumns {
                    const val TABLE_NAME = "users"
                    const val COLUMN_USERNAME = "username"
                }
            }
            
            // UserDbHelper.kt
            class UserDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
                companion object {
                    private const val DATABASE_NAME = "Users.db"
                    private const val DATABASE_VERSION = 1
                }

                override fun onCreate(db: SQLiteDatabase) {
                    db.execSQL(""$/{'"'}
                        CREATE TABLE $/{UserContract.UserEntry.TABLE_NAME} (
                            $/{BaseColumns._ID} INTEGER PRIMARY KEY,
                            $/{UserContract.UserEntry.COLUMN_USERNAME} TEXT NOT NULL
                        )
                    ""$/{'"'})
                }

                override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
                    db.execSQL("DROP TABLE IF EXISTS $/{UserContract.UserEntry.TABLE_NAME}")
                    onCreate(db)
                }
            }
            
            // UserProvider.kt
            class UserProvider : ContentProvider() {
                private lateinit var dbHelper: UserDbHelper

                companion object {
                    private const val USERS = 1
                    private const val USER_ID = 2
                    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
                        addURI(UserContract.AUTHORITY, UserContract.PATH_USERS, USERS)
                        addURI(UserContract.AUTHORITY, "$/{UserContract.PATH_USERS}/#", USER_ID)
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
                                    UserContract.UserEntry.TABLE_NAME, projection, "$/{BaseColumns._ID} = ?",
                                    arrayOf(id), null, null, sortOrder)
                            }
                            else -> throw IllegalArgumentException("Unknown URI: $/uri")
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
                            throw SQLException("Failed to insert row into $/uri")
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
                                    UserContract.UserEntry.TABLE_NAME, values, "$/{BaseColumns._ID} = ?",
                                    arrayOf(id))
                            }
                            else -> throw IllegalArgumentException("Unknown URI: $/uri")
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
                                db.delete(UserContract.UserEntry.TABLE_NAME, "$/{BaseColumns._ID} = ?", arrayOf(id))
                            }
                            else -> throw IllegalArgumentException("Unknown URI: $/uri")
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
                            USERS -> "vnd.android.cursor.dir/$/{UserContract.AUTHORITY}.$/{UserContract.PATH_USERS}"
                            USER_ID -> "vnd.android.cursor.item/$/{UserContract.AUTHORITY}.$/{UserContract.PATH_USERS}"
                            else -> throw IllegalArgumentException("Unknown URI: $/uri")
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Error getting type for URI: $/uri", e)
                        null
                    }
                }
            }
            
            // usage
            val context = LocalContext.current
            var name by remember { mutableStateOf("Mayank") }

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Enter Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = {
                        try {
                            val values = ContentValues().apply {
                                put(UserContract.UserEntry.COLUMN_USERNAME, name)
                            }
                            val newUri = context.contentResolver.insert(UserContract.CONTENT_URI, values)
                            if (newUri != null) {
                                Log.d("UserProvider", "Inserted new user with URI: $/newUri")
                                Toast.makeText(context, "Inserted new user", Toast.LENGTH_SHORT).show()
                            } else {
                                Log.e("UserProvider", "Failed to insert user")
                                Toast.makeText(context, "Failed to insert user", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            Log.e("UserProvider", "Error inserting user", e)
                        }
                    }
                ) {
                    Text("Insert Username")
                }

                Button(
                    onClick = {
                        try {
                            val cursor = context.contentResolver.query(UserContract.CONTENT_URI, null, null, null, null)
                            cursor?.use {
                                val usernames = mutableListOf<String>()
                                while (it.moveToNext()) {
                                    val username = it.getString(it.getColumnIndexOrThrow(
                                        UserContract.UserEntry.COLUMN_USERNAME))
                                    usernames.add(username)
                                }
                                Toast.makeText(context, "Retrieved usernames: $/{usernames.joinToString()}", Toast.LENGTH_SHORT).show()
                                Log.d("UserProvider", "Retrieved usernames: $/{usernames.joinToString()}")
                            } ?: Log.e("UserProvider", "Query returned null cursor")
                        } catch (e: Exception) {
                            Log.e("UserProvider", "Error retrieving usernames", e)
                        }
                    }
                ) {
                    Text("Retrieve Usernames")
                }

                Button(onClick = {
                    val rowsDeleted = context.contentResolver.delete(
                        UserContract.CONTENT_URI,
                        null,
                        null
                    )
                    Toast.makeText(context, "Deleted $/rowsDeleted users", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Clear Usernames")
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun ContentProviderScreenPreview() {
    ContentProviderScreen(navController = rememberNavController())
}