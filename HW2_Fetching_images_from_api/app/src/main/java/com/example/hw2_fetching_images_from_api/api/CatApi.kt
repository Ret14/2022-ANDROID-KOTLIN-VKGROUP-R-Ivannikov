package com.example.hw2_fetching_images_from_api.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatApi {
    @GET("search")
    suspend fun fetchPhotos(@Header("x-api-key") key: String,
                            @Query("page") page: Int,
                            @Query("limit") amount: Int): List<PhotoItem>
}