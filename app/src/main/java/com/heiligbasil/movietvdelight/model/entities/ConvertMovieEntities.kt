package com.heiligbasil.movietvdelight.model.entities

object ConvertMovieEntities {
    fun MovieTopRatedResult.toMovieEssentials() = MovieEssentials(
        id = movieId,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        saved = false
    )

    fun MovieEssentials.toMovieTopRatedResult() = MovieTopRatedResult(
        adult = false,
        backdropPath = backdropPath,
        genreIds = listOf(0),
        movieId = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = 0.0,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = false,
        voteAverage = voteAverage,
        voteCount = 0
    )
}