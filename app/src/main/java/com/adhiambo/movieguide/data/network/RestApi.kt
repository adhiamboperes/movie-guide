package com.adhiambo.movieguide.data.network

import com.adhiambo.movieguide.common.api_key
import com.adhiambo.movieguide.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {
    @GET("discover/movie?api_key=$api_key")
    fun getMovies(): Call<MovieResponse>
}