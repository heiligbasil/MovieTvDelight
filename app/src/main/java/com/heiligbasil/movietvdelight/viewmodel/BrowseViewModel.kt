package com.heiligbasil.movietvdelight.viewmodel

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.heiligbasil.movietvdelight.model.BrowseRepository
import com.heiligbasil.movietvdelight.model.entities.MovieTopRatedResult

class BrowseViewModel(private val repository: BrowseRepository) : ViewModel(), Observable {

    private lateinit var movie: MovieTopRatedResult

    fun init(movieTopRatedResult: MovieTopRatedResult) {
        movie = movieTopRatedResult
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}