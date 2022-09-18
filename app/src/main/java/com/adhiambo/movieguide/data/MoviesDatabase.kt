package com.adhiambo.movieguide.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adhiambo.movieguide.common.DATABASE_NAME
import com.adhiambo.movieguide.data.local.MovieDao
import com.adhiambo.movieguide.data.local.MovieModel

@Database(entities = [MovieModel::class], version = 2)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var dbInstance: MoviesDatabase? = null

        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MoviesDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

        operator fun invoke(context: Context) = dbInstance ?: synchronized(LOCK) {
            dbInstance ?: buildDatabase(context).also { dbInstance = it }
        }
    }
}