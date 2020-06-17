package com.heiligbasil.movietvdelight.viewmodel

import android.os.Parcelable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.remote.RemoteRepository
import kotlinx.coroutines.launch

class BrowseViewModel(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val localMovies: LiveData<List<MovieEssentials>> = localRepository.movies
    private lateinit var remoteMovies: LiveData<List<MovieEssentials>>

    // Accessible copy of the local database list of records
    val dbList = ArrayList<MovieEssentials>()

    // Hash Map to store a key-value pair of the movie ID and saved flag for O(1) access time
    val savedHash = HashMap<Int, Boolean>()
    var selectedTabPosition = 0
    var listState: Parcelable? = null

    fun getMovies() = remoteMovies

    fun getLocalMovies() = localRepository.movies

    fun getSavedMovies() = localRepository.saved

    fun setTabChoice(tabPosition: Int) {
        selectedTabPosition = tabPosition
        if (tabPosition == 0)
            remoteMovies = remoteRepository.getMtrMutableLiveData()
        else
            remoteMovies = remoteRepository.getTtrMutableLiveData()
    }

    fun storeMovies() = viewModelScope.launch {
        // Iterate through the movies from the server and compare to see if any changes are present
        remoteMovies.value?.forEach {
            // Check if the server movie exists in the local database
            if (savedHash.containsKey(it.id)) {
                // Check if the movie has had its saved status modified
                if (savedHash[it.id] != it.saved) {
                    // Update the existing record with the new data
                    localRepository.update(it)
                }
            } else {
                // The movie does not exist in the local database, so add it
                localRepository.insert(it)
            }
        }
    }

    fun updateMovie(movie: MovieEssentials) = viewModelScope.launch {
        localRepository.update(movie)
    }
}