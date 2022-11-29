package com.example.hw2_fetching_images_from_api

import com.example.hw2_fetching_images_from_api.api.CatApi
import com.example.hw2_fetching_images_from_api.api.PhotoItem
import retrofit2.HttpException
import java.io.IOException

class PhotoRepository(
    private val catApi: CatApi
) {
    suspend fun fetchPhotos(page: Int, amount: Int): List<PhotoItem> {
        try {
            return catApi.fetchPhotos(key = Consts.apiKey, page = page, amount = amount)
        } catch (e: IOException) {
            throw Exception(Consts.ioeExceptionFlag)
        } catch (e: HttpException) {
            throw Exception(Consts.httpExceptionFlag)
        }
    }
}