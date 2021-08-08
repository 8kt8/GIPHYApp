package com.example.giphyapp.core.service.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GifsListResponse(
    @Json(name = "data")
    val data: List<GifsDataResponse>
)

@JsonClass(generateAdapter = true)
data class GifsResponse(
    @Json(name = "data")
    val data: GifsDataResponse
)

@JsonClass(generateAdapter = true)
data class GifsDataResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "images")
    val images: GifsImagesResponse,
    @Json(name = "title")
    val title: String,
    @Json(name = "source_tld")
    val source: String,
    @Json(name = "rating")
    val ratingCode: String
)

@JsonClass(generateAdapter = true)
data class GifsImagesResponse(
    @Json(name = "original")
    val original: GifsOriginalImageResponse,
    @Json(name = "fixed_height_small")
    val small: GifsSmallImageResponse
)

@JsonClass(generateAdapter = true)
data class GifsOriginalImageResponse(
    @Json(name = "url")
    val url: String
)

@JsonClass(generateAdapter = true)
data class GifsSmallImageResponse(
    @Json(name = "url")
    val url: String
)