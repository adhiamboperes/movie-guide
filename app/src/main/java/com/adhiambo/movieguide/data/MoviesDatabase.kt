package com.adhiambo.movieguide.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adhiambo.movieguide.common.DATABASE_NAME

@Database(entities = [MovieModel::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun dataDao(): MovieDao

    companion object {
        @Volatile
        private var dbInstance: MoviesDatabase? = null

        private val LOCK = Any()

        private fun buildDatabase(context: Context,) =
            synchronized(this) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    DATABASE_NAME
                )
                    .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING)
                    .fallbackToDestructiveMigration()

                builder.build()
            }

        operator fun invoke(context: Context) = dbInstance ?: synchronized(LOCK) {
            dbInstance ?: buildDatabase(context).also { dbInstance = it }
        }
    }
}