package com.adhiambo.movieguide.data.usecase

import com.adhiambo.movieguide.config.MovieGuideException
import com.adhiambo.movieguide.data.Movie
import com.adhiambo.movieguide.data.network.MovieNetworkSource
import com.adhiambo.movieguide.data.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class GetMoviesUseCaseTest {
    private val movieRepository = mock<MovieRepository>()

    private val moviesList = mutableListOf<Movie>()

    private lateinit var moviesUseCase: GetMoviesUseCase

    private val networkSource = mock<MovieNetworkSource>()

    @Before
    fun setUp() {
        (0 until 2).forEach {
            moviesList.add(MovieFactory.build(id = it))
        }

        moviesUseCase = GetMoviesUseCase()
    }

    @Test
    fun `return list of movies when network call is successful and save to db is successful`() {
        whenever(movieRepository.getMovies()).thenReturn(Single.just(moviesList))
        whenever(movieRepository.saveMovies(any())).thenReturn(Completable.complete())
        whenever(movieRepository.getMoviesFromDb()).thenReturn(Single.just(moviesList))

        val spyUseCase = spy(moviesUseCase)
        doReturn(Single.just(moviesList))
            .whenever(spyUseCase)
            .execute()

        val movies = spyUseCase.execute().blockingGet()

        verify(movieRepository).getMovies()
        verify(movieRepository).saveMovies(movies)
        verify(movieRepository).getMoviesFromDb()
        verify(spyUseCase).execute()
    }

    @Test
    fun `return list of movies when get from network call succeeds and save to db fails`() {
        whenever(movieRepository.getMovies()).thenReturn(Single.just(moviesList))
        whenever(movieRepository.saveMovies(any())).thenReturn(Completable.error(some_exception))
        whenever(movieRepository.getMoviesFromDb()).thenReturn(Single.just(moviesList))

        val spyUseCase = spy(moviesUseCase)
        doReturn(Single.just(moviesList))
            .whenever(spyUseCase)
            .execute()

        val movies = spyUseCase.execute().blockingGet()

        verify(movieRepository).getMovies()
        verify(movieRepository).saveMovies(movies)
        verify(movieRepository).getMoviesFromDb()
        verify(spyUseCase).execute()
    }

    @Test
    fun `return list of movies from db when network call fails due to network error`() {
        whenever(movieRepository.getMovies()).thenReturn(Single.error(some_exception))
        whenever(movieRepository.getMoviesFromDb()).thenReturn(Single.just(moviesList))

        val spyUseCase = spy(moviesUseCase)
        doReturn(Single.just(moviesList))
            .whenever(spyUseCase)
            .execute()

        verify(movieRepository).getMoviesFromDb()
        verify(spyUseCase).execute()
    }

    @Test
    fun `return an exception when fetch from network and fetch from db fails`() {
        whenever(movieRepository.getMovies()).thenReturn(Single.error(some_exception))
        whenever(movieRepository.getMoviesFromDb()).thenReturn(Single.error(some_exception))

        val spyUseCase = spy(moviesUseCase)
        doReturn(Single.error<MovieGuideException>(some_exception))
            .whenever(spyUseCase)
            .execute()

        verify(movieRepository).getMovies()
        verify(movieRepository).getMoviesFromDb()
        verify(spyUseCase).execute()
    }

    companion object {
        val some_exception = MovieGuideException(msg = "some exception")
    }
}