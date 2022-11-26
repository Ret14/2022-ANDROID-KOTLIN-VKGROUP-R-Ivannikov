package com.example.hw2_fetching_images_from_api

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw2_fetching_images_from_api.api.DogApi
import com.example.hw2_fetching_images_from_api.databinding.FragmentPhotoListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

const val TAG_CHECK_RESPONSE = "TAG_CHECK_RESPONSE"

class PhotoListFragment : Fragment() {
    private var _binding: FragmentPhotoListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    private val pagingAdapter = PhotoListAdapter(PhotoComparator)
    private val photoListViewModel: PhotoListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoListBinding.inflate(inflater, container, false)
        binding.rvPhotoList.layoutManager = GridLayoutManager(context, 2)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                photoListViewModel.photoItems.collect {
                    pagingAdapter.submitData(it)
                    pagingAdapter.addLoadStateListener { state: CombinedLoadStates ->
                        binding.rvPhotoList.isVisible = state.refresh != LoadState.Loading
                        binding.pbLarge.isVisible = state.refresh == LoadState.Loading
                    }

                    binding.rvPhotoList.adapter = pagingAdapter.withLoadStateFooter(
                        footer = PhotoLoadStateAdapter{ pagingAdapter.retry() })
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}