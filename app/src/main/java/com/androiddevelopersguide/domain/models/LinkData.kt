package com.androiddevelopersguide.domain.models

import java.util.UUID

data class LinkData(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val routePath: String
)