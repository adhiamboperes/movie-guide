package com.adhiambo.movieguide.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adhiambo.movieguide.OpenForTesting
import com.adhiambo.movieguide.common.room.MovieRoomModel

@Entity(
    tableName = "movies"
)
@OpenForTesting
data class MovieModel(
    @PrimaryKey val id: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val year: String,
    @ColumnInfo val rating: String,
    @ColumnInfo val duration: String,
    @ColumnInfo val poster: String
) : MovieRoomModel<Movie> {

    constructor(movie: Movie) : this(
        id = movie.id,
        title = movie.title,
        year = movie.year,
        rating = movie.rating,
        duration = movie.duration,
        poster = movie.poster
    )

    override fun toDomainObject(): Movie {
        return Movie(
            title = title,
            year = year,
            rating = rating,
            duration = duration,
            poster = poster,
            id = id
        )
    }
}