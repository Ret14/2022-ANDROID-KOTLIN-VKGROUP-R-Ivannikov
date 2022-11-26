package com.example.hw2_fetching_images_from_api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.hw2_fetching_images_from_api.api.PhotoItem
import kotlinx.coroutines.flow.*


private const val TAG = "PhotoListViewModel"

class PhotoListViewModel: ViewModel() {
    private val photoRepository = PhotoRepository()
    val photoItems: StateFlow<PagingData<PhotoItem>> =
        Pager(PagingConfig(pageSize = 20)) {
            PhotoPagingSource(photoRepository) }.flow
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}