package com.hishd.kotlintmdbdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hishd.kotlintmdbdemo.R
import com.hishd.kotlintmdbdemo.databinding.RowMovieItemBinding
import com.hishd.kotlintmdbdemo.model.MovieModel
import com.squareup.picasso.Picasso

class MovieListAdapter(private val callback: (MovieModel) -> Unit): RecyclerView.Adapter<MovieListAdapterViewHolder>() {

    private var movieList: List<MovieModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapterViewHolder {
        val binding = RowMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListAdapterViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: MovieListAdapterViewHolder, position: Int) {
        with(movieList[position]) {
            holder.bind(this, callback = callback)
        }
    }

    override fun getItemCount(): Int {
        return movieList.count()
    }

    fun setData(movies: List<MovieModel>) {
        this.movieList = movies
        notifyDataSetChanged()
    }
}

class MovieListAdapterViewHolder(val binding: RowMovieItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieModel, callback: (MovieModel) -> Unit) {
        with(movie) {
            binding.lblId.text = "Movie ID : $id"
            binding.lblTitle.text = "Movie Title : ${title?: name}"
            binding.lblOverview.text = "$overview"

            Picasso
                .get()
                .load("https://image.tmdb.org/t/p/original$poster_path")
                .into(binding.imgPoster)
        }

        binding.root.setOnClickListener {
            callback(movie)
        }
    }
}