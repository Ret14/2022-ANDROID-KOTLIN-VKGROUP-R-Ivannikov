package com.example.hw1_numbered_block_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw1_numbered_block_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnAddItem.setOnClickListener { btnAddOnClick() }
        setContentView(binding.root)
    }
    private fun btnAddOnClick() {
        val blockList = binding.fragmentContainer.getFragment<BlockListFragment>()
        blockList.addItem()
    }
}