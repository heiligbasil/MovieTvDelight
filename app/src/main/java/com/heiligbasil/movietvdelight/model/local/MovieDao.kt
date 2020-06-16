package com.heiligbasil.movietvdelight.model.local

import androidx.room.*
import com.heiligbasil.movietvdelight.model.entities.MovieTopRatedResult

@Dao
interface MovieDao {
    @Insert
    suspend fun insertMovie(movie: MovieTopRatedResult): Long

    @Update
    suspend fun updateMovie(movie: MovieTopRatedResult): Int

    @Delete
    suspend fun deleteMovie(movie: MovieTopRatedResult): Int

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): List<MovieTopRatedResult>
}