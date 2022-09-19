package com.adhiambo.movieguide.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import com.adhiambo.movieguide.BuildConfig
import com.adhiambo.movieguide.R
import com.adhiambo.movieguide.common.MovieGuideApp
import com.adhiambo.movieguide.data.Movie
import com.adhiambo.movieguide.databinding.ItemMovieBinding
import com.adhiambo.movieguide.presentation.UiUtils.getYear

class MoviesAdapter :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val movies = mutableListOf<Movie>()

    inner class ViewHolder(private val viewBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bindItems(movie: Movie) {
            viewBinding.itemMovieTitle.text = movie.title
            viewBinding.itemMovieYear.text = movie.year.getYear()
            viewBinding.itemMovieRating.text = movie.rating
            val posterUrl = buildString {
                append(BuildConfig.image_url)
                append(movie.poster)
            }
            loadMoviePosters(posterUrl, viewBinding.itemMoviePoster)
            showMovieAgeRatingSymbol(movie.adult, viewBinding.itemMovieRatingSymbol)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movies[holder.adapterPosition].let { holder.bindItems(it) }
    }

    override fun getItemCount() = movies.size

    fun addItems(items: List<Movie>) {
        movies.clear()
        movies.addAll(items)
        notifyDataSetChanged()
    }

    private fun loadMoviePosters(url: String, target: ImageView) {
        val imageLoader = ImageLoader(MovieGuideApp.instance)
        val request = ImageRequest.Builder(MovieGuideApp.instance)
            .data(url)
            .crossfade(true)
            .target(target)
            .build()

        imageLoader.enqueue(request)
    }

    private fun showMovieAgeRatingSymbol(adult: Boolean, view: ImageView) {
        if (!adult) {
            view.setBackgroundResource(R.drawable.ic_rating_g)
        } else view.setBackgroundResource(R.drawable.ic_rating_r)
    }
}