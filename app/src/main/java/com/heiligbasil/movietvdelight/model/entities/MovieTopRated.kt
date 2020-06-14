package com.heiligbasil.movietvdelight.model.entities

import com.google.gson.annotations.SerializedName


data class MovieTopRated(
    val page: Int,

    val results: List<MovieTopRatedResult>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)