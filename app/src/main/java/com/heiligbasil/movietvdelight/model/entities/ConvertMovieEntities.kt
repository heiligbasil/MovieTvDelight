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
        year = releaseDate.substring(0..3),
        title = title,
        voteAverage = voteAverage,
        saved = false
    )

    /**
     * Convert an object of type **MovieEssentials** to **MovieTopRatedResult**
     */
    fun MovieEssentials.toMovieTopRatedResult() = MovieTopRatedResult(
        adult = false,
        backdropPath = backdropPath,
        genreIds = listOf(0),
        movieId = id,
        originalLanguage = language,
        originalTitle = originalTitle,
        overview = overview,
        popularity = 0.0,
        posterPath = posterPath,
        releaseDate = "$year-01-01",
        title = title,
        video = false,
        voteAverage = voteAverage,
        voteCount = 0
    )
}