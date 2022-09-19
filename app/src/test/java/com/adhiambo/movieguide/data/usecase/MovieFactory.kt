package com.adhiambo.movieguide.data.usecase

import com.adhiambo.movieguide.data.Movie

object MovieFactory {

    fun build(
        id: Int = 1,
        title: String = "Foo baz",
        year: String = "2020-11-11",
        rating: String = "7.8",
        adult: Boolean = false,
        poster: String = "img_poster.jpg"
    ) = Movie(
        id = id,
        title = title,
        year = year,
        rating = rating,
        adult = adult,
        poster = poster
    )
}