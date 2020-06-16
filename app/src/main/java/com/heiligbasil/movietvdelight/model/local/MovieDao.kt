package com.heiligbasil.movietvdelight.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials

@Dao
interface MovieDao {
    @Insert
    suspend fun insertMovie(movie: MovieEssentials): Long

    @Update
    suspend fun updateMovie(movie: MovieEssentials): Int

    @Delete
    suspend fun deleteMovie(movie: MovieEssentials): Int

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<MovieEssentials>>
}