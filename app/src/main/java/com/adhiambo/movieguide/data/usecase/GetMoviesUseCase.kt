package com.adhiambo.movieguide.data.usecase

import com.adhiambo.movieguide.OpenForTesting
import com.adhiambo.movieguide.data.Movie
import com.adhiambo.movieguide.data.repository.MovieRepository
import io.reactivex.Single

@OpenForTesting
class GetMoviesUseCase {
    private val movieRepository = MovieRepository()

    fun execute(): Single<List<Movie>> {
        return movieRepository.getMovies()
            .doOnSuccess {
                movieRepository.saveMovies(it)
            }
            .flatMap {
                movieRepository.getMoviesFromDb()
            }
            .onErrorResumeNext {
                movieRepository.getMoviesFromDb()
            }
    }

}