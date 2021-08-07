package com.example.giphyapp.core.service

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {

    @GET("trending")
    fun getTrending(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: String = "25",
        @Query("rating") rating: String = "g" ,
    ): Single<TrendingGifsResponse>
}
