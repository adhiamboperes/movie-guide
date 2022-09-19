package com.adhiambo.movieguide.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adhiambo.movieguide.data.Movie
import com.adhiambo.movieguide.data.usecase.GetMoviesUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMoviesUseCase
) : ViewModel() {

    val movies = MutableLiveData<List<Movie>>()
    val fetchStateLiveData = MutableLiveData<FetchState>()

    fun getMovies() {
        val disposable = CompositeDisposable()
        disposable.add(
            getMovieUseCase.execute()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    movies.postValue(it)
                }, {
                    fetchStateLiveData.postValue(FetchState.FETCH_FAILED)
                })
        )
    }

    enum class FetchState {
        FETCH_SUCCESS,
        FETCH_FAILED
    }
}