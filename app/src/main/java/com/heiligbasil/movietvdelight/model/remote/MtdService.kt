package com.heiligbasil.movietvdelight.model.remote

import com.heiligbasil.movietvdelight.model.entities.MovieDetail
import com.heiligbasil.movietvdelight.model.entities.MovieTopRated
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MtdService {
    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") enUs: String,
        @Query("page") page: Int
    ): Response<MovieTopRated>

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String,
        @Query("language") enUs: String
    ): Response<MovieDetail>

    @GET("3/search/movie")
    suspend fun searchMovieTitles(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Response<MovieTopRated>
}