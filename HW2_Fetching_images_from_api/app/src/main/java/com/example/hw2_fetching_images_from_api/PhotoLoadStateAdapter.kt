package com.example.hw2_fetching_images_from_api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_fetching_images_from_api.databinding.DefaultLoadStateBinding

class PhotoLoadStateAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<PhotoLoadStateHolder>() {
    override fun onBindViewHolder(holder: PhotoLoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PhotoLoadStateHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DefaultLoadStateBinding.inflate(inflater, parent, false)
        return PhotoLoadStateHolder(binding, retry)
    }
}

class PhotoLoadStateHolder(
    private val binding: DefaultLoadStateBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private fun getErrorMessage(state: LoadState.Error): Int {
        return when (state.error.message) {
            Consts.ioeExceptionFlag -> R.string.bad_internet_error_massage
            Consts.httpExceptionFlag -> R.string.bad_http_code_error_massage
            else -> R.string.default_error_message
        }
    }
    fun bind(loadState: LoadState) {
        binding.apply {
            btnRetry.isVisible = loadState !is LoadState.Loading
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
            pbLoadState.isVisible = loadState is LoadState.Loading
            if (loadState is LoadState.Error) tvErrorMessage.setText(getErrorMessage(loadState))
            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }
}