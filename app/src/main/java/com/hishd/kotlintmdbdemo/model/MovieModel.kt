package com.hishd.kotlintmdbdemo.model

data class MovieModel(
    val id: Int,
    val backdrop_path: String,
    val title: String?,
    val overview: String,
    val poster_path: String,
    val name: String?
    )