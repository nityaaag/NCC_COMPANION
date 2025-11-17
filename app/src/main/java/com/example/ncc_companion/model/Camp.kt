package com.example.ncc_companion.model

data class Camp(
    val id: String,
    val title: String,
    val category: String,
    val duration: String,
    val description: String,
    val highlights: List<String>
)
