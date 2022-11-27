package com.example.hw2_fetching_images_from_api

import android.os.Bundle
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw2_fetching_images_from_api.databinding.FragmentPhotoListBinding
import kotlinx.coroutines.launch

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
        binding.rvPhotoList.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter(pagingAdapter, binding)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                photoListViewModel.photoItems.collect {
                    pagingAdapter.submitData(it)
                    binding.rvPhotoList.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                        footer = PhotoLoadStateAdapter{ pagingAdapter.retry() },
                        header = PhotoLoadStateAdapter{ pagingAdapter.retry() })
                }
            }
        }
    }

    private fun initAdapter(
        adapter: PhotoListAdapter,
        binding: FragmentPhotoListBinding
    ) {
        adapter.addLoadStateListener { state: CombinedLoadStates ->
            binding.apply {
                rvPhotoList.isVisible = state.refresh != LoadState.Loading
                pbLarge.isVisible = state.refresh == LoadState.Loading
                btnRetry.isVisible = state.refresh is LoadState.Error
                tvErrorMessage.isVisible = state.refresh is LoadState.Error
                if (state.refresh is LoadState.Error){
                    tvErrorMessage.text = (state.refresh as LoadState.Error).error.localizedMessage
                }
            }
        }
        binding.btnRetry.setOnClickListener { adapter.retry() }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}