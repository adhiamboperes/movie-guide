package com.adhiambo.movieguide.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("")
    var title: String,
    @SerializedName("")
    var year: String,
    @SerializedName("")
    var rating: String,
    @SerializedName("")
    var duration: String
)
