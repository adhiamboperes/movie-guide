package com.adhiambo.movieguide.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title")
    var title: String,
    @SerializedName("release_date")
    var year: String,
    @SerializedName("vote_average")
    var rating: String,
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("poster_path")
    var poster: String,
    @SerializedName("id")
    var id: Int
)
