package com.adhiambo.movieguide.data.repository

import com.adhiambo.movieguide.common.MovieGuideApp
import com.adhiambo.movieguide.data.Movie
import com.adhiambo.movieguide.data.local.MovieModel
import com.adhiambo.movieguide.data.network.MovieNetworkSource
import io.reactivex.Single

class MovieRepository {
    private val networkSource = MovieNetworkSource()

    fun getMovies(): Single<List<Movie>> {
        return networkSource.getMovies()
    }

    fun getMoviesFromDb(): Single<List<Movie>> {
        return MovieGuideApp.database.movieDao().getMovies().map { movies ->
            movies.map { it.toDomainObject() }
        }
    }

    fun saveMovies(movies: List<Movie>) {
        MovieGuideApp.database.movieDao().insertMovies(movies.map { MovieModel(it) })
    }
}