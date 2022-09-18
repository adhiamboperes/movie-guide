package com.adhiambo.movieguide.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adhiambo.movieguide.OpenForTesting
import com.adhiambo.movieguide.common.room.MovieRoomModel
import com.adhiambo.movieguide.data.Movie

@Entity(
    tableName = "movies"
)
@OpenForTesting
data class MovieModel(
    @PrimaryKey val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val year: String,
    @ColumnInfo val rating: String,
    @ColumnInfo val adult: Boolean,
    @ColumnInfo val poster: String
) : MovieRoomModel<Movie> {

    constructor(movie: Movie) : this(
        id = movie.id,
        title = movie.title,
        year = movie.year,
        rating = movie.rating,
        adult = movie.adult,
        poster = movie.poster
    )

    override fun toDomainObject(): Movie {
        return Movie(
            title = title,
            year = year,
            rating = rating,
            adult = adult,
            poster = poster,
            id = id
        )
    }
}