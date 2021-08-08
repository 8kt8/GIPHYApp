package com.example.giphyapp.core.service

import com.example.giphyapp.core.service.model.GifsResponse
import com.example.giphyapp.core.service.model.GifsListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiphyService {

    @GET("trending")
    fun getTrending(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: String = "50"
    ): Single<GifsListResponse>

    @GET("search")
    fun search(
        @Query("q") query: String,
        @Query("api_key") apiKey: String,
        @Query("limit") limit: String = "50"
    ): Single<GifsListResponse>

    @GET("{id}")
    fun getById(
        @Path("id") query: String,
        @Query("api_key") apiKey: String,
    ): Single<GifsResponse>
}
