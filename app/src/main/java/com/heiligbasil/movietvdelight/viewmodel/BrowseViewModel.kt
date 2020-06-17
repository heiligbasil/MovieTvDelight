package com.heiligbasil.movietvdelight.viewmodel

import android.os.Parcelable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.remote.RemoteRepository

class BrowseViewModel(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ViewModel(), Observable {

    private lateinit var localMovies: LiveData<List<MovieEssentials>>// = localRepository.movies
    private lateinit var remoteMovies: LiveData<List<MovieEssentials>>
    private lateinit var movie: MovieEssentials
    var selectedTabPosition = 0
    var listState: Parcelable? = null

    fun init(movie: MovieEssentials) {
        this.movie = movie
    }

    fun setTabChoice(tabPosition: Int) {
        selectedTabPosition = tabPosition
        if (tabPosition == 0)
            remoteMovies = remoteRepository.getMtrMutableLiveData()
        else
            remoteMovies = remoteRepository.getTtrMutableLiveData()
    }

    fun getMovies(): LiveData<List<MovieEssentials>> {
        return remoteMovies
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}