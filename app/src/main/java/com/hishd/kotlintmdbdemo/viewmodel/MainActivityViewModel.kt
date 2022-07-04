package com.hishd.kotlintmdbdemo.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.hishd.kotlintmdbdemo.model.MovieModel
import com.hishd.kotlintmdbdemo.services.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class MainActivityViewModel(private val context: WeakReference<Context>) : ViewModel() {
    private var mMovieList = liveData(Dispatchers.IO) {
        context.get()?.let { context ->
            val movies = APIService.loadMovies(context)
            emit(movies)
        }
    }
    val movieList: LiveData<List<MovieModel>> get() = mMovieList

//    fun loadData() {
//        context.get()?.let { context ->
//            viewModelScope.launch {
//                mMovieList.value = APIService.loadMovies(context)
//            }
//        }
//    }
}