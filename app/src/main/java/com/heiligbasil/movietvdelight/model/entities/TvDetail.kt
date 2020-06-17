package com.heiligbasil.movietvdelight.model.entities
import com.google.gson.annotations.SerializedName


data class TvDetail(
    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("created_by")
    val createdBy: List<String>,

    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    val genres: List<Genre>,

    val homepage: String,

    val id: Int,

    @SerializedName("in_production")
    val inProduction: Boolean,

    val languages: List<String>,

    @SerializedName("last_air_date")
    val lastAirDate: String,

    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: TvLastEpisodeToAir,

    val name: String,

    val networks: List<TvNetwork>,

    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: String,

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,

    @SerializedName("origin_country")
    val originCountry: List<String>,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_name")
    val originalName: String,

    val overview: String,

    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,

    val seasons: List<TvSeason>,

    val status: String,

    val type: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
)