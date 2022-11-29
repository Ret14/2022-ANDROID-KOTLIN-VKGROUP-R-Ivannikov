package com.example.hw2_fetching_images_from_api.api

import com.example.hw2_fetching_images_from_api.Consts
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object CatApiObj {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Consts.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val api: CatApi = retrofit.create()
}