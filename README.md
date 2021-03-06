# MovieTvDelight
Discover which popular movies and TV shows are trending!

## Description
This Android app allows the user to search for movies, browse a list of top-rated movies/TV shows, and save movies for later reference.

## Features
Android app coded in Kotlin. Single activity with multiple fragments and RecyclerViews. REST API calls using Retrofit. Kotlin coroutines. Picasso library image loading. Shared Preferences to save the current location. SearchView integration to pull movie info based on any title. LiveData to observe changes in data. Navigation Component to streamline fragment management. MVVM architecture with ViewModel. Room persistent library. Data binding for the layouts, Picasso image loading, and the model entity.

## Screenshots
[<img src="img/screenshot1.png?raw=true" width = "275" />](img/screenshot1.png)&nbsp;&nbsp;&nbsp;&nbsp;[<img src="img/screenshot2.png?raw=true" width = "275" />](img/screenshot2.png)&nbsp;&nbsp;&nbsp;&nbsp;[<img src="img/screenshot3.png?raw=true" width = "275" />](img/screenshot3.png) [<img src="img/screenshot4.png?raw=true" width = "275" />](img/screenshot4.png)&nbsp;&nbsp;&nbsp;&nbsp;[<img src="img/screenshot5.png?raw=true" width = "275" />](img/screenshot5.png)&nbsp;&nbsp;&nbsp;&nbsp;[<img src="img/screenshot6.png?raw=true" width = "275" />](img/screenshot6.png)

## Notes
To run it, you must create a file in the root directory called `apikey.properties` and put in your TMDB API key inside like so: API_KEY="1234abc…xyz"

And if you need a [TMDB (The Movie Database)](https://www.themoviedb.org/) API key, you can go to the [developer documentation](https://www.themoviedb.org/documentation/api), sign up for a free account, and request one.
