package com.heiligbasil.movietvdelight.model.entities

/**
 * Static class to hold Kotlin extension functions
 */
object ConvertMovieEntities {

    /**
     * Convert an object of type **MovieTopRatedResult** to **MovieEssentials**
     */
    fun MovieTopRatedResult.toMovieEssentials() = MovieEssentials(
        id = movieId,
        backdropPath = backdropPath,
        language = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        year = releaseDate.pluckYear(),
        title = title,
        voteAverage = voteAverage,
        saved = false
    )

    /**
     * Convert an object of type **MovieEssentials** to **MovieTopRatedResult**
     */
    fun MovieEssentials.toMovieTopRatedResult() = MovieTopRatedResult(
        adult = false,
        backdropPath = backdropPath ?: "",
        genreIds = listOf(0),
        movieId = id,
        originalLanguage = language ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = 0.0,
        posterPath = posterPath ?: "",
        releaseDate = "$year-01-01",
        title = title ?: "",
        video = false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = 0
    )

    /**
     * Convert an object of type **TvTopRatedResult** to **MovieEssentials**
     */
    fun TvTopRatedResult.toMovieEssentials() = MovieEssentials(
        id = tvId,
        backdropPath = backdropPath ?: "",
        language = originalLanguage,
        originalTitle = originalName,
        overview = overview,
        posterPath = posterPath,
        year = firstAirDate,
        title = name,
        voteAverage = voteAverage,
        saved = false
    )

    /**
     * Convert an object of type **MovieEssentials** to **TvTopRatedResult**
     */
    fun MovieEssentials.toTvTopRatedResult() = TvTopRatedResult(
        backdropPath = backdropPath,
        genreIds = listOf(0),
        tvId = id,
        originCountry = listOf(""),
        originalLanguage = language ?: "",
        originalName = originalTitle ?: "",
        overview = overview ?: "",
        popularity = 0.0,
        posterPath = posterPath ?: "",
        firstAirDate = year ?: "",
        name = title ?: "",
        voteAverage = voteAverage ?: 0.0,
        voteCount = 0
    )

    /**
     * Remove the year from the start of the string if it exists
     */
    fun String.pluckYear(): String {
        if (this.length > 4)
            return this.substring(0..3)
        else
            return this
    }
}