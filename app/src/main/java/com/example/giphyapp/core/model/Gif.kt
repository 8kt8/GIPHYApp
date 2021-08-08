package com.example.giphyapp.core.model

data class Gif(
    val id: String,
    val originalUrl: String,
    val smallUrl: String,
    val title: String,
    val source: String,
    val ratingCode: String,
    val pageUrl: String
)