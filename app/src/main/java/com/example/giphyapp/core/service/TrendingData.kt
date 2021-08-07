package com.example.giphyapp.core.service

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
    @Json(name = "title")
    val title: String
)