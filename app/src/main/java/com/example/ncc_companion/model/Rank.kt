package com.example.ncc_companion.model

data class Rank(
    val id: String,
    val title: String,
    val level: String,
    val wing: Wing,
    val imageRes: Int,
    val description: String,
    val responsibilities: String,
    val badgeMeaning: String,
    val promotionCriteria: String,
    val training: String
)

enum class Wing(val displayName: String) {
    ARMY("Army Wing"),
    NAVY("Navy Wing"),
    AIR("Air Wing")
}
