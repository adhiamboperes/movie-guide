package com.adhiambo.movieguide.data

import com.google.gson.annotations.SerializedName

open class MovieResponse(
    @SerializedName("results")
    var results: List<Movie>
)
