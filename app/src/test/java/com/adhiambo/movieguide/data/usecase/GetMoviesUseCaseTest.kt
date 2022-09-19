package com.adhiambo.movieguide.data.usecase

import com.adhiambo.movieguide.data.Movie
import com.adhiambo.movieguide.data.repository.MovieRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.doNothing
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class GetMoviesUseCaseTest {
    private val movieRepository = mock<MovieRepository>()

    private val moviesList = mutableListOf<Movie>()

    private lateinit var moviesUseCase: GetMoviesUseCase

    @Before
    fun setUp() {
        (0 until 2).forEach {
            moviesList.add(MovieFactory.build(id = it))
        }

        moviesUseCase = GetMoviesUseCase(movieRepository)
    }

    @Test
    fun `return list of movies when network call is successful and save to db is successful`() {
        whenever(movieRepository.getMovies()).thenReturn(Single.just(moviesList))
        doNothing().`when`(movieRepository).saveMovies(any())
        whenever(movieRepository.getMoviesFromDb()).thenReturn(Single.just(moviesList))

        val spyUseCase = spy(moviesUseCase)

        val movies = spyUseCase.execute().blockingGet()

        verify(movieRepository).getMovies()
        verify(movieRepository).saveMovies(movies)
        verify(movieRepository).getMoviesFromDb()
        verify(spyUseCase).execute()
    }

    @Test
    fun `return list of movies when get from network call succeeds and save to db fails`() {
        whenever(movieRepository.getMovies()).thenReturn(Single.just(moviesList))
        doThrow(some_exception).`when`(movieRepository).saveMovies(any())
        whenever(movieRepository.getMoviesFromDb()).thenReturn(Single.just(moviesList))

        val spyUseCase = spy(moviesUseCase)
        val movies = spyUseCase.execute().blockingGet()

        verify(movieRepository).getMovies()
        verify(movieRepository).saveMovies(movies)
        verify(movieRepository).getMoviesFromDb()
        verify(spyUseCase).execute()
    }

    @Test
    fun `return list of movies from db when network call fails`() {
        whenever(movieRepository.getMovies()).thenReturn(Single.error(Throwable(some_exception)))
        whenever(movieRepository.getMoviesFromDb()).thenReturn(Single.just(moviesList))

        val spyUseCase = spy(moviesUseCase)
        spyUseCase.execute().blockingGet()

        verify(movieRepository).getMoviesFromDb()
        verify(spyUseCase).execute()
    }

    @Test
    fun `return an exception when fetch from network and fetch from db fails`() {

        val spyUseCase = spy(moviesUseCase)
        doThrow(some_exception)
            .whenever(spyUseCase)
            .execute()

        assertThrows<java.lang.RuntimeException> {
            spyUseCase.execute()
        }
        verifyNoInteractions(movieRepository)
    }

    companion object {
        val some_exception = RuntimeException("some exception")
    }
}