package com.example.hw2_fetching_images_from_api

import com.example.hw2_fetching_images_from_api.api.DogApi
import com.example.hw2_fetching_images_from_api.api.PhotoItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class PhotoRepository {
    private val dogApi: DogApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/images/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        dogApi = retrofit.create()
    }
    suspend fun fetchPhotos(page: Int, amount: Int): List<PhotoItem> {
        return dogApi.fetchPhotos(page = page, amount = amount)
    }
}