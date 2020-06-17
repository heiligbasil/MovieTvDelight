package com.heiligbasil.movietvdelight.model.entities

import com.google.gson.annotations.SerializedName

data class TvTopRated(
    val page: Int,

    val results: List<TvTopRatedResult>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)