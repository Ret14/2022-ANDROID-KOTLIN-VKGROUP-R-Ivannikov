package com.example.hw2_fetching_images_from_api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hw2_fetching_images_from_api.api.PhotoItem
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "PhotoPagingSource"

class PhotoPagingSource(
    private val photoRepository: PhotoRepository
): PagingSource<Int, PhotoItem>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoItem> {
        val page = params.key ?: 0
        val pageSize = params.loadSize.coerceAtMost(100)
        try {
            val photos = photoRepository.fetchPhotos(page, pageSize)
            val nextKey = if (photos.size < pageSize) null else page + 1
            val prevKey = if (page == 0) null else page - 1
            return LoadResult.Page(photos, prevKey, nextKey)
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}