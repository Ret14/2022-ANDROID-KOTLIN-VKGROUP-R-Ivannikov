package com.example.hw2_fetching_images_from_api

import com.example.hw2_fetching_images_from_api.api.CatApi
import com.example.hw2_fetching_images_from_api.api.PhotoItem
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.IOException

class PhotoRepository {
    private val catApi: CatApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/images/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        catApi = retrofit.create()
    }
    suspend fun fetchPhotos(page: Int, amount: Int): List<PhotoItem> {
        try {
            return catApi.fetchPhotos(page = page, amount = amount)
        } catch (e: IOException) {
            throw Exception("No internet")
        }
        catch (e: HttpException) {
            throw Exception("Bad response code")
        }
    }
}