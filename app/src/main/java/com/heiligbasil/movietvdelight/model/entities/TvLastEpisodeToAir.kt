package com.heiligbasil.movietvdelight.model.entities

import com.google.gson.annotations.SerializedName

data class TvLastEpisodeToAir(
    @SerializedName("air_date")
    val airDate: String,

    @SerializedName("episode_number")
    val episodeNumber: Int,

    val id: Int,

    val name: String,

    val overview: String,

    @SerializedName("production_code")
    val productionCode: String,

    @SerializedName("season_number")
    val seasonNumber: Int,

    @SerializedName("show_id")
    val showId: Int,

    @SerializedName("still_path")
    val stillPath: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
)