package com.heiligbasil.movietvdelight.model.remote

import com.google.gson.GsonBuilder
import com.heiligbasil.movietvdelight.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import java.util.concurrent.TimeUnit

class Retrofit {

    companion object {

        const val apiKey = BuildConfig.API_KEY
        const val baseUrl = "https://api.themoviedb.org/"
        const val baseImageUrl = "https://image.tmdb.org/t/p/"
        const val smallSize = "w154/"
        const val largeSize = "w342/"
        const val backdropSize = "w500/"

        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
        }.build()

        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        fun buildPosterUrl(filename: String, large: Boolean = false): String {
            val url1 = URL(baseImageUrl)
            val url2 = URL(url1, if (large) largeSize else smallSize)
            val url3 = URL(url2, filename.removePrefix("/"))
            return url3.toString()
        }
    }
}