package com.heiligbasil.movietvdelight.model.remote

import com.heiligbasil.movietvdelight.BuildConfig
import com.heiligbasil.movietvdelight.model.entities.MovieDetail
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class accessTmdb {

//    fun getMovieDetails(movieId:String) {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.themoviedb.org/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(MovieInterface::class.java)
//
//        val call = service.getMovieDetails(movieId, apiKey, "en-US")
//        call.enqueue(object : Callback<MovieDetail> {
//            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
//                text_view_movie_details.text = t.message
//            }
//
//            override fun onResponse(call: Call<MovieDetail>, response: Response<TmdbModel>) {
//                val tmdbModel = response.body() as TmdbModel
//                val movieInfo = "${tmdbModel.title} - ${tmdbModel.date}"
//                text_view_movie_details.text = movieInfo
//
//                Picasso.get()
//                    .load("https://image.tmdb.org/t/p/original/${tmdbModel.image}")
//                    .into(image_view)
//            }
//        })
//    }
//
//    private fun getTopRatedMovies(movies: List<Int>) {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.themoviedb.org/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(TmdbInterface::class.java)
//        movies.forEach {
//            val call = service.getMovie(it.toString(), api_key.text.toString(), "en-US")
//            call.enqueue(object : Callback<TmdbModel> {
//                override fun onFailure(call: Call<TmdbModel>, t: Throwable) {
//                    addMovieToRecyclerView(
//                        TmdbModel(
//                            "",
//                            t.message ?: "Error Loading",
//                            "http://www.google.com/",
//                            "/bptfVGEQuv6vDTIMVCHjJ9Dz8PX.jpg"
//                        )
//                    )
//                }
//
//                override fun onResponse(call: Call<TmdbModel>, response: Response<TmdbModel>) {
//                    val tmdbModel = response.body() as TmdbModel
//                    addMovieToRecyclerView(tmdbModel)
//                }
//            })
//        }
//    }
}