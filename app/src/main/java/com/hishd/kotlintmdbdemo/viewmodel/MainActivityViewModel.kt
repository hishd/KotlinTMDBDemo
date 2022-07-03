package com.hishd.kotlintmdbdemo.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hishd.kotlintmdbdemo.model.MovieModel
import com.hishd.kotlintmdbdemo.services.APIService
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class MainActivityViewModel(private val context: WeakReference<Context>) : ViewModel() {
    private var mMovieList = MutableLiveData<List<MovieModel>>()
    val movieList: LiveData<List<MovieModel>> get() = mMovieList

    fun loadData() {
        context.get()?.let { context ->
            viewModelScope.launch {
                mMovieList.value = APIService.loadMovies(context)
            }
        }
    }
}