package com.heiligbasil.movietvdelight.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_table")
data class MovieEssentials(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movie_id")
    val id: Int,

    @ColumnInfo(name = "movie_backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "movie_original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "movie_original_title")
    val originalTitle: String,

    @ColumnInfo(name = "movie_overview")
    val overview: String,

    @ColumnInfo(name = "movie_poster_path")
    val posterPath: String,

    @ColumnInfo(name = "movie_release_date")
    val releaseDate: String,

    @ColumnInfo(name = "movie_title")
    val title: String,

    @ColumnInfo(name = "movie_vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "movie_saved")
    val saved: Boolean
)