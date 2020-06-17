package com.heiligbasil.movietvdelight.model.remote

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.heiligbasil.movietvdelight.model.entities.ConvertMovieEntities.toMovieEssentials
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.model.entities.MovieTopRated
import com.heiligbasil.movietvdelight.model.entities.TvTopRated
import retrofit2.Response

class RemoteRepository(private val lco: LifecycleOwner) {

    private val mtvLiveData = MutableLiveData<List<MovieEssentials>>()
    private val movieService = Retrofit.getInstance().create(MtdService::class.java)
    private val tvService = Retrofit.getInstance().create(TtdService::class.java)

    fun getMtrMutableLiveData(): MutableLiveData<List<MovieEssentials>> {
        val movies = ArrayList<MovieEssentials>()

        val mtrResponse = liveData<Response<MovieTopRated>> {
            val response = movieService.getTopRatedMovies(Retrofit.apiKey, "enUS", 1)
            emit(response)
        }

        mtrResponse.observe(lco, Observer {
            val mtr = it.body()
            val topRatedMoviesIterator = mtr?.results?.listIterator()
            if (topRatedMoviesIterator !== null) {
                while (topRatedMoviesIterator.hasNext()) {
                    val topRatedMovie = topRatedMoviesIterator.next()
                    movies.add(topRatedMovie.toMovieEssentials())
                }
                mtvLiveData.value = movies
            }
        })

        return mtvLiveData
    }

    fun getTtrMutableLiveData(): MutableLiveData<List<MovieEssentials>> {
        val movies = ArrayList<MovieEssentials>()

        val ttrResponse = liveData<Response<TvTopRated>> {
            val response = tvService.getTopRatedTvShows(Retrofit.apiKey, "enUS", 1)
            emit(response)
        }

        ttrResponse.observe(lco, Observer {
            val ttr = it.body()
            val topRatedTvIterator = ttr?.results?.listIterator()
            if (topRatedTvIterator !== null) {
                while (topRatedTvIterator.hasNext()) {
                    val topRatedTvSeries = topRatedTvIterator.next()
                    movies.add(topRatedTvSeries.toMovieEssentials())
                }
                mtvLiveData.value = movies
            }
        })

        return mtvLiveData
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