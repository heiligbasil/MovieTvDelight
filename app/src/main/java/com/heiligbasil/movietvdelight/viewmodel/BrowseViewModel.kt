package com.heiligbasil.movietvdelight.viewmodel

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.heiligbasil.movietvdelight.model.BrowseRepository

class BrowseViewModel(private val repository: BrowseRepository):ViewModel(),Observable {

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}