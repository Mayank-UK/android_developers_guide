package com.androiddevelopersguide.domain.models

/**
 * User-selectable theme mode. [System] follows the platform's dark-theme setting.
 */
enum class AppTheme {
    Light,
    Dark,
    System;

    companion object {
        /** Storage key used by [com.androiddevelopersguide.domain.repositoryinterfaces.ThemeRepository]. */
        const val STORAGE_KEY = "rdg-theme"

        /** Parses a persisted [name]; falls back to [System] for unknown/missing values. */
        fun safeFromString(name: String?): AppTheme =
            entries.firstOrNull { it.name == name } ?: System
    }
}
