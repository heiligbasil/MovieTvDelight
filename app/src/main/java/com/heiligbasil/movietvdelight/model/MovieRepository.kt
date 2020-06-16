package com.heiligbasil.movietvdelight.model

import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.model.local.MovieDao

class MovieRepository(private val dao: MovieDao) {
    val movies = dao.getAllMovies()

    suspend fun insert(movie: MovieEssentials): Long {
        return dao.insertMovie(movie)
    }

    suspend fun update(movie: MovieEssentials): Int {
        return dao.updateMovie(movie)
    }

    suspend fun delete(movie: MovieEssentials): Int {
        return dao.deleteMovie(movie)
    }
}