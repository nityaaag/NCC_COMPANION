package com.example.ncc_companion.model

data class ResourceLink(
    val id: String,
    val title: String,
    val description: String,
    val url: String
)

data class PdfResource(
    val id: String,
    val title: String,
    val description: String
)

data class UploadedPdf(
    val name: String,
    val uri: String
)
