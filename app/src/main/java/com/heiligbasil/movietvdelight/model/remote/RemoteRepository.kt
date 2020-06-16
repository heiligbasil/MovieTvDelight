package com.heiligbasil.movietvdelight.model.remote

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.model.entities.ConvertMovieEntities.toMovieEssentials
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.model.entities.MovieTopRated
import com.heiligbasil.movietvdelight.model.entities.MovieTopRatedResult
import com.heiligbasil.movietvdelight.model.remote.MtdService
import com.heiligbasil.movietvdelight.model.remote.Retrofit
import retrofit2.Response

class RemoteRepository(private val lco:LifecycleOwner) {

//    private val topRatedMovies = ArrayList<MovieTopRatedResult>()
    private val topRatedMoviesLiveData = MutableLiveData<List<MovieEssentials>>()

    fun getMtrMutableLiveData(): MutableLiveData<List<MovieEssentials>> {

        val tmdbService = Retrofit.getInstance().create(MtdService::class.java)

        val mtrResponse = liveData<Response<MovieTopRated>> {
            val response = tmdbService.getTopRatedMovies(Retrofit.apiKey, "enUS", 1)
            emit(response)
        }

        val movies = ArrayList<MovieEssentials>()

        mtrResponse.observe(lco, Observer {
            val mtr = it.body()
            val topRatedMoviesIterator = mtr?.results?.listIterator()
            if (topRatedMoviesIterator !== null) {
                while (topRatedMoviesIterator.hasNext()) {
                    val topRatedMovie = topRatedMoviesIterator.next()
                    movies.add(topRatedMovie.toMovieEssentials())
                }
                topRatedMoviesLiveData.value=movies
            }
        })

        return topRatedMoviesLiveData
    }

    /*fun getTopRatedMoviesMutableLiveData(): MutableLiveData<List<MovieTopRatedResult>> {

        val tmdbService = Retrofit.getInstance().create(MtdService::class.java)

        val mtrResponse = liveData<Response<MovieTopRated>> {
            val lang = application.applicationContext.getString(R.string.language_en_us)
            val response = tmdbService.getTopRatedMovies(Retrofit.apiKey, lang, 1)
            emit(response)
        }

        mtrResponse.observe(this, Observer {
            val mtr = it.body()
            val topRatedMovies = mtr?.results?.listIterator()
            if (topRatedMovies !== null) {
                while (topRatedMovies.hasNext()) {
                    val topRatedMovie = topRatedMovies.next()
                    Log.i("test321", topRatedMovie.title)
                }
            }
        })
    }*/
}