package com.example.hw2_fetching_images_from_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw2_fetching_images_from_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}