package com.heiligbasil.movietvdelight.model.entities

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heiligbasil.movietvdelight.model.remote.Retrofit
import com.squareup.picasso.Picasso

@Entity(tableName = "movie_table")
data class MovieEssentials(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movie_id")
    val id: Int,

    @ColumnInfo(name = "movie_backdrop_path")
    val backdropPath: String,

    @Bindable
    @ColumnInfo(name = "movie_language")
    val language: String,

    @ColumnInfo(name = "movie_original_title")
    val originalTitle: String,

    @Bindable
    @ColumnInfo(name = "movie_overview")
    val overview: String,

    @ColumnInfo(name = "movie_poster_path")
    val posterPath: String,

    @Bindable
    @ColumnInfo(name = "movie_year")
    val year: String,

    @Bindable
    @ColumnInfo(name = "movie_title")
    val title: String,

    @ColumnInfo(name = "movie_vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "movie_saved")
    val saved: Boolean
) : BaseObservable() {
    companion object {
        @JvmStatic
        @BindingAdapter("posterPath")
        fun loadPoster(imageView: ImageView, url: String) {
            val posterUrl = Retrofit.buildPosterUrl(url)
            Picasso.get().load(posterUrl).into(imageView)
        }
    }
}