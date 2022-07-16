package dev.ogabek.android_saa.model

import java.io.Serializable

data class Image(
    val id: String,
    val description: String? = null,
    val alt_description: String? = null,
    val updated_at: String,
    val width: Long,
    val height: Long,
    val color: String,
    val urls: Urls,
    val links: Links,
    val likes: Long,
    val liked_by_user: Boolean,
    val user: User
) : Serializable
