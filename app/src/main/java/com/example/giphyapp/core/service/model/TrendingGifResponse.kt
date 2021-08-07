package com.example.giphyapp.core.service.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrendingGifsResponse(
    @Json(name = "data")
    val data: List<TrendingGifsDataResponse>,
)

@JsonClass(generateAdapter = true)
data class TrendingGifsDataResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "images")
    val images: TrendingGifsImagesResponse,
    @Json(name = "title")
    val title: String
)

@JsonClass(generateAdapter = true)
data class TrendingGifsImagesResponse(
    @Json(name = "original")
    val original: TrendingGifsOriginalImageResponse,
    @Json(name = "fixed_height_small")
    val small: TrendingGifsSmallImageResponse
)

@JsonClass(generateAdapter = true)
data class TrendingGifsOriginalImageResponse(
    @Json(name = "url")
    val url: String
)

@JsonClass(generateAdapter = true)
data class TrendingGifsSmallImageResponse(
    @Json(name = "url")
    val url: String
)