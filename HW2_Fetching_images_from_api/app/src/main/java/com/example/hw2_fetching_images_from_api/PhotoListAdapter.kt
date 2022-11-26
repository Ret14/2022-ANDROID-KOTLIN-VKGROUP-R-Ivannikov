package com.example.hw2_fetching_images_from_api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.load
import com.example.hw2_fetching_images_from_api.api.PhotoItem
import com.example.hw2_fetching_images_from_api.databinding.ListItemPhotoBinding

class PhotoViewHolder(
    private val binding: ListItemPhotoBinding
): RecyclerView.ViewHolder(binding.root) {
    private val imageLoader = ImageLoader.Builder(binding.root.context)
        .components { add(GifDecoder.Factory()) }
        .build()

    fun bind(photoItem: PhotoItem?) {
        binding.ivPhoto.load(photoItem?.url, imageLoader = imageLoader) {
            placeholder(R.drawable.image_placeholder)
        }
    }
}

class PhotoListAdapter(diffCallback: DiffUtil.ItemCallback<PhotoItem>) :
        PagingDataAdapter<PhotoItem, PhotoViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPhotoBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

}

object PhotoComparator : DiffUtil.ItemCallback<PhotoItem>() {
    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem == newItem
    }
}