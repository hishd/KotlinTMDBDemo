package com.hishd.kotlintmdbdemo.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hishd.kotlintmdbdemo.adapters.MovieListAdapter
import com.hishd.kotlintmdbdemo.model.MovieModel
import com.hishd.kotlintmdbdemo.services.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ref.WeakReference

class MainActivityViewModel(private val context: WeakReference<Context>) : ViewModel() {
    private val movieList = mutableListOf<MovieModel>()
    private val movieListAdapter: MovieListAdapter by lazy {
        MovieListAdapter(movieList = this.movieList, callback = this.callback)
    }
    val adapter: MovieListAdapter get() = this.movieListAdapter

    fun loadData() {
        context.get()?.let { context ->
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    movieList.addAll(APIService.loadMovies(context))
                }
                movieListAdapter.notifyDataSetChanged()
            }
        }
    }

    private val callback: (movie: MovieModel) -> Unit = {
        Toast.makeText(context.get(), "Movie name is ${it.name ?: it.title}", Toast.LENGTH_LONG)
            .show()
    }
}