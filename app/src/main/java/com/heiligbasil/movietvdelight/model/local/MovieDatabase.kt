package com.heiligbasil.movietvdelight.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials

@Database(entities = [MovieEssentials::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movie_database"
                    ).build()
                }
                return instance
            }
        }
    }
}