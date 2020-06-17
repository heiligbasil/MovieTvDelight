package com.heiligbasil.movietvdelight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.FragmentSavedBinding
import com.heiligbasil.movietvdelight.model.entities.Utils
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.local.MovieDatabase
import com.heiligbasil.movietvdelight.viewmodel.SavedViewModel
import com.heiligbasil.movietvdelight.viewmodel.SavedViewModelFactory

class SavedFragment : OptionsMenuFragment() {

    private lateinit var binding: FragmentSavedBinding
    private lateinit var viewModel: SavedViewModel
    private lateinit var adapter: SavedMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = MovieDatabase.getInstance(view.context).movieDao
        val localRepository = LocalRepository(dao)
        val factory = SavedViewModelFactory(localRepository)
        viewModel = ViewModelProvider(this, factory).get(SavedViewModel::class.java)
        binding.savedViewModel = viewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        // Save the current location using Shared Preferences
        Utils.saveLocation(view.context, 2)
    }

    private fun initRecyclerView() {
        binding.savedRecyclerViewContainer.layoutManager = LinearLayoutManager(this.context)
        adapter = SavedMovieAdapter()
        binding.savedRecyclerViewContainer.adapter = adapter
        addListOfMovies()
    }

    private fun addListOfMovies() {
        // Add all of the movie objects into the Recycler View
        viewModel.getSavedMovies().observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }
}