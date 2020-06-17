package com.heiligbasil.movietvdelight.model.local

import com.heiligbasil.movietvdelight.model.entities.MovieEssentials

class LocalRepository(private val dao: MovieDao) {
    val movies = dao.getAllMovies()
    val saved = dao.getAllSavedMovies()

    suspend fun insert(movie: MovieEssentials): Long = dao.insertMovie(movie)

    suspend fun update(movie: MovieEssentials): Int = dao.updateMovie(movie)

    suspend fun delete(movie: MovieEssentials): Int = dao.deleteMovie(movie)
}