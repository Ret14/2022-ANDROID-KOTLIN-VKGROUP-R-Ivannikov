package com.example.hw1_numbered_block_list

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hw1_numbered_block_list.databinding.FragmentBlockListBinding

class BlockListFragment: Fragment() {

    private var _binding: FragmentBlockListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null"
        }

    private val currentOrientation
    get() = resources.configuration.orientation
    private val adapter
    get() = binding.rvBlockList.adapter

    private val blockListViewModel: BlockListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlockListBinding.inflate(inflater, container, false)
        binding.rvBlockList.layoutManager = when (currentOrientation) {
            Configuration.ORIENTATION_LANDSCAPE -> GridLayoutManager(context, 4)
            else -> GridLayoutManager(context, 3)
        }
        val adapter = BlockListAdapter(blockListViewModel.blocks)
        binding.rvBlockList.adapter = adapter
        return binding.root
    }
    fun addItem() {
        blockListViewModel.addBlock()
        adapter?.notifyItemInserted(blockListViewModel.totalBlocks - 1)
        binding.rvBlockList.scrollToPosition(blockListViewModel.totalBlocks - 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}