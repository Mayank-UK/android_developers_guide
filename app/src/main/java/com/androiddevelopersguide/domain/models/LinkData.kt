package com.androiddevelopersguide.domain.models

import java.util.UUID

/**
 * Stable, UI-framework-free description of a navigable link. The optional [icon] is a
 * [LinkIcon] key resolved to an `ImageVector` in the UI layer, keeping the domain layer free
 * of Android Compose types. [category] groups links on the home grid; non-home screens leave
 * it null.
 */
data class LinkData(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val routePath: String,
    val description: String? = null,
    val icon: LinkIcon? = null,
    val category: String? = null
)

/**
 * Icon keys stored alongside [LinkData]. Pure Kotlin — no Compose/Android UI dependency.
 * The UI layer maps each value to a concrete `androidx.compose.ui.graphics.vector.ImageVector`.
 */
enum class LinkIcon {
    ANDROID,
    KOTLIN,
    COMPOSE,
    BASICS,
    LIFECYCLE,
    WIDGETS,
    DEPENDENCY,
    API,
    DATABASE,
    ARCHITECTURE,
    DEPLOYMENT,
    BEST_PRACTICES,
    XML
}
