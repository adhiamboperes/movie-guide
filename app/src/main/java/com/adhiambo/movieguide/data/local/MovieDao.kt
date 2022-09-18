package com.adhiambo.movieguide.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query(
        """
            SELECT *
            FROM movies
            WHERE movies.id = :movieId
            LIMIT 1
        """
    )
    fun getMovieWithId(movieId: Int): Single<MovieModel>

    @Query(
        """
            SELECT *
            FROM movies
        """
    )
    fun getMovies(): Single<List<MovieModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(data: List<MovieModel>)
}