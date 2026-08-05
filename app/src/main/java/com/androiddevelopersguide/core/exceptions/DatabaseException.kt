package com.androiddevelopersguide.core.exceptions

/**
 * Local-persistence failures: read/write/delete/connection errors from the database layer.
 *
 * Each instance carries a [kind] discriminator so downstream mappers can branch on a stable
 * type instead of substring-matching the prose [message] (which is fragile and locale-prone).
 */
class DatabaseException(
    val kind: Kind,
    message: String = "Database error",
    cause: Throwable? = null,
    context: String? = null
) : AppException(message, cause, context) {

    enum class Kind { READ, WRITE, DELETE, CONNECTION, GENERIC }

    companion object {
        fun readFailed(cause: Throwable? = null, context: String? = null) =
            DatabaseException(Kind.READ, "Failed to read from database", cause, context)

        fun writeFailed(cause: Throwable? = null, context: String? = null) =
            DatabaseException(Kind.WRITE, "Failed to write to database", cause, context)

        fun deleteFailed(cause: Throwable? = null, context: String? = null) =
            DatabaseException(Kind.DELETE, "Failed to delete from database", cause, context)

        fun connectionFailed(cause: Throwable? = null, context: String? = null) =
            DatabaseException(Kind.CONNECTION, "Database connection failed", cause, context)
    }
}
