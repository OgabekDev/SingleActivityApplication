package dev.ogabek.android_saa.model

import java.io.Serializable

data class Social(
    val instagram_username: String? = null,
    val portfolio_url: String? = null,
    val twitter_username: String? = null,
) : Serializable