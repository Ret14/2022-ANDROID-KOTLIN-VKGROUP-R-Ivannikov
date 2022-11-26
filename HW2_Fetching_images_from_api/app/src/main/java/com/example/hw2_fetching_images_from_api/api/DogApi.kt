package com.example.hw2_fetching_images_from_api.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val API_KEY = "live_D3L5tFGrA49Qb9cr5cfpHIvY7NFTJjvOWMoJH8oGUXOjIb3n6KlDittSoQNdbAHA"

interface DogApi {
    @GET("search")
    suspend fun fetchPhotos(@Header("x-api-key") key: String = API_KEY,
                            @Query("page") page: Int,
                            @Query("limit") amount: Int): List<PhotoItem>
}