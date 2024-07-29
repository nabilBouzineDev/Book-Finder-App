package com.nabilbdev.bookfinder.data.remote.model

import com.nabilbdev.bookfinder.data.utils.DEFAULT_COVER_URL
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)

@Serializable
data class Item(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val authors: List<String> = listOf("Unknown Author"),
    val averageRating: Double = 0.0,
    val categories: List<String> = listOf("Undefined"),
    val description: String = "Description not available",
    val imageLinks: ImageLinks = ImageLinks(
        smallThumbnail = DEFAULT_COVER_URL,
        thumbnail = DEFAULT_COVER_URL
    ),
    val previewLink: String = "Preview not found!",
    val publishedDate: String = "Date not available",
    val title: String = "Title not available"
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)