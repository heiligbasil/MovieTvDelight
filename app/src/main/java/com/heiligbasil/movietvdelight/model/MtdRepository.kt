package com.heiligbasil.movietvdelight.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.model.entities.MovieTopRated
import com.heiligbasil.movietvdelight.model.entities.MovieTopRatedResult
import com.heiligbasil.movietvdelight.model.remote.MtdService
import com.heiligbasil.movietvdelight.model.remote.Retrofit
import retrofit2.Response

class MtdRepository(private val application: Application) {

    private val topRatedMovies = ArrayList<MovieTopRatedResult>()

    private val topRatedMoviesMutableLiveData = MutableLiveData<List<MovieTopRatedResult>>()

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