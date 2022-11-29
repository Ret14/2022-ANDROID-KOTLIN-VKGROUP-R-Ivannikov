package com.example.hw2_fetching_images_from_api.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoItem(
    @Json(name = "url") val url: String,
    @Json(name = "id") val id: String
)