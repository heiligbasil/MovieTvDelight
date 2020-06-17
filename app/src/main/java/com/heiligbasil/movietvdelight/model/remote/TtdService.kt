package com.heiligbasil.movietvdelight.model.remote

import com.heiligbasil.movietvdelight.model.entities.TvDetail
import com.heiligbasil.movietvdelight.model.entities.TvTopRated
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TtdService {
    @GET("3/tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("api_key") apiKey: String,
        @Query("language") enUs: String,
        @Query("page") page: Int
    ): Response<TvTopRated>

    @GET("3/tv/{tvId}")
    suspend fun getTvShowDetails(
        @Path("tvId") tvId: String,
        @Query("api_key") apiKey: String,
        @Query("language") enUs: String
    ): Response<TvDetail>
}