package com.example.giphyapp.core.service

import javax.inject.Inject

class BackendConfig @Inject constructor() {
    val apiUrl = "https://api.giphy.com/v1/gifs/"
    val version = "v1"
    val apiKey = "YhpyjIJN4N4l3a1xvAt6qyHjqpFf5xfx"
    val readTimeout = 60L
    val connectionTimeout = 60L
}