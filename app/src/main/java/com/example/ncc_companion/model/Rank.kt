package com.example.ncc_companion.model

import androidx.annotation.DrawableRes

enum class Wing(val displayName: String) {
    ARMY("Army"),
    NAVY("Navy"),
    AIR("Air Wing")
}

data class Rank(
    val id: String,
    val title: String,
    val level: String,
    val description: String,
    val wing: Wing,
    @DrawableRes val imageRes: Int
)
