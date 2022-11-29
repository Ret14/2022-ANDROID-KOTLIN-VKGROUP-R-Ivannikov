package com.example.hw2_fetching_images_from_api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hw2_fetching_images_from_api.api.CatApiObj
import com.example.hw2_fetching_images_from_api.api.PhotoItem
import kotlinx.coroutines.flow.*

class PhotoListViewModel: ViewModel() {
    private val photoRepository = PhotoRepository(CatApiObj.api)
    val photoItems: StateFlow<PagingData<PhotoItem>> =
        Pager(PagingConfig(pageSize = 20)) {
            PhotoPagingSource(photoRepository, maxLoadSize = 100, startPage = 0)
        }.flow
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}