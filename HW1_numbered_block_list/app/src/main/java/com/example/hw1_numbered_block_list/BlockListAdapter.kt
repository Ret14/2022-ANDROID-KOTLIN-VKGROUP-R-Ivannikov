package com.example.hw1_numbered_block_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hw1_numbered_block_list.databinding.ListItemBlockBinding

class BlockHolder(
    private val binding: ListItemBlockBinding
    ): ViewHolder(binding.root) {
    fun bind(block: Block) {
        binding.tvBlock.apply {
            text = block.value.toString()
            setBackgroundResource(block.color)
        }
    }
}

class BlockListAdapter(
    private val blocks: MutableList<Block>
): Adapter<BlockHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBlockBinding.inflate(inflater, parent, false)
        return BlockHolder(binding)
    }

    override fun onBindViewHolder(holder: BlockHolder, position: Int) {
        val block = blocks[position]
        holder.bind(block)
    }

    override fun getItemCount() = blocks.size

}