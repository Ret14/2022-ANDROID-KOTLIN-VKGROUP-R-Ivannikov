package com.example.hw1_numbered_block_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TOTAL_BLOCKS = "TOTAL_BLOCKS"

class BlockListViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    val blocks = mutableListOf<Block>()
    var totalBlocks
    get() = savedStateHandle[TOTAL_BLOCKS] ?: 0
    set(value) = savedStateHandle.set(TOTAL_BLOCKS, value)

    init {
        if (totalBlocks != 0) {
            val restoreBlocksAmount = totalBlocks
            totalBlocks = 0
            for (i in 1..restoreBlocksAmount)
                addBlock()
        }
    }
    fun addBlock() {
        val newValue = blocks.size
        if (newValue % 2 == 0) blocks.add(Block(newValue, R.color.red))
        else blocks.add(Block(newValue, R.color.blue))
        totalBlocks++
    }
}