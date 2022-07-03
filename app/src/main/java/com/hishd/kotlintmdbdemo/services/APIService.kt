package com.hishd.kotlintmdbdemo.services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hishd.kotlintmdbdemo.model.MovieModel
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object APIService {
    private val apiKey: String = "4e48e73eafce3743af952bbcc82bc72d"
    private val url: String = "https://api.themoviedb.org/3/trending/all/week?api_key=$apiKey"


    suspend fun loadMovies(context: Context) = suspendCoroutine<List<MovieModel>> { continuation ->
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            {
                val data = it.getJSONArray("results")
                val movies = mutableListOf<MovieModel>()
                for(index in 0 until data.length()) {
                    movies.add(Gson().fromJson(data.getString(index), MovieModel::class.java))
                }
                continuation.resume(movies)
            },
            {
                Log.e("API SERVICE", it.toString())
                throw APIServiceException("Error $it")
            })

        Volley.newRequestQueue(context).add(request)
    }
}

open class APIServiceException(message: String): Exception(message)