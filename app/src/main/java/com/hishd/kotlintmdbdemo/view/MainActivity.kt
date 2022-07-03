package com.hishd.kotlintmdbdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hishd.kotlintmdbdemo.adapters.MovieListAdapter
import com.hishd.kotlintmdbdemo.viewmodel.MainActivityViewModel
import com.hishd.kotlintmdbdemo.databinding.ActivityMainBinding
import com.hishd.kotlintmdbdemo.model.MovieModel
import com.hishd.kotlintmdbdemo.services.APIService
import com.hishd.kotlintmdbdemo.viewmodel.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRes()
        viewModel.loadData()
    }

    private fun setupRes() {
        viewModelFactory = MainActivityViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
        binding.recyclerMovies.layoutManager = LinearLayoutManager(this)
        binding.recyclerMovies.adapter = viewModel.adapter
    }
}