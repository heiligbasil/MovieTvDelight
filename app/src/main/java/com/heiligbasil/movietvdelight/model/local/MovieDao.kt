package com.heiligbasil.movietvdelight.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: MovieEssentials): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movie: MovieEssentials): Int

    @Delete
    suspend fun deleteMovie(movie: MovieEssentials): Int

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<MovieEssentials>>

    @Query("SELECT * FROM movie_table WHERE movie_saved = :saved")
    fun getAllSavedMovies(saved: Boolean = true): LiveData<List<MovieEssentials>>
}