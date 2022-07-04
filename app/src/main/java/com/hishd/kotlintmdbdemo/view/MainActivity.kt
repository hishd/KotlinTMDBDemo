package com.hishd.kotlintmdbdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hishd.kotlintmdbdemo.adapters.MovieListAdapter
import com.hishd.kotlintmdbdemo.viewmodel.MainActivityViewModel
import com.hishd.kotlintmdbdemo.databinding.ActivityMainBinding
import com.hishd.kotlintmdbdemo.model.MovieModel
import com.hishd.kotlintmdbdemo.services.APIService
import com.hishd.kotlintmdbdemo.viewmodel.MainActivityViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRes()
        setupObservers()

//        lifecycleScope.launchWhenStarted {
//            viewModel.loadData()
//        }
    }

    private fun setupRes() {
        viewModelFactory = MainActivityViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
        binding.recyclerMovies.layoutManager = LinearLayoutManager(this)
        movieListAdapter = MovieListAdapter(callback = this.callback)
        binding.recyclerMovies.adapter = movieListAdapter
    }

    private fun setupObservers() {
        viewModel.movieList.observe(this) {
            movieListAdapter.setData(it)
        }
    }

    private val callback: (movie: MovieModel) -> Unit = {
        Toast.makeText(this, "Movie name is ${it.name ?: it.title}", Toast.LENGTH_LONG)
            .show()
    }
}