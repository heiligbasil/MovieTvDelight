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
import com.heiligbasil.movietvdelight.databinding.FragmentSearchBinding
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.local.MovieDatabase
import com.heiligbasil.movietvdelight.model.remote.RemoteRepository
import com.heiligbasil.movietvdelight.viewmodel.SavedViewModel
import com.heiligbasil.movietvdelight.viewmodel.SavedViewModelFactory
import com.heiligbasil.movietvdelight.viewmodel.SearchViewModel
import com.heiligbasil.movietvdelight.viewmodel.SearchViewModelFactory

class SearchFragment : OptionsMenuFragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = MovieDatabase.getInstance(view.context).movieDao
        val localRepository = LocalRepository(dao)
        val remoteRepository = RemoteRepository(viewLifecycleOwner)
        val factory = SearchViewModelFactory(localRepository, remoteRepository)
        viewModel = ViewModelProvider(this, factory).get(SearchViewModel::class.java)
        binding.searchViewModel = viewModel
        binding.lifecycleOwner = this

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.searchRecyclerViewContainer.layoutManager = LinearLayoutManager(this.context)
        adapter = SearchMovieAdapter()
        binding.searchRecyclerViewContainer.adapter = adapter
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