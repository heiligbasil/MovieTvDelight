package com.heiligbasil.movietvdelight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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

        // Specify behavior when searching for movie titles
        binding.searchSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val modifiedQuery = query.toString().replace(' ', '+')
                viewModel.dbList.clear()
                viewModel.searchWithQuery(modifiedQuery).observe(viewLifecycleOwner, Observer {
                    it.forEach { movie ->
                        viewModel.dbList.add(movie)
                    }
                    initRecyclerView()
                    viewModel.storeMovies()
                })

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun initRecyclerView() {
        binding.searchRecyclerViewContainer.layoutManager = LinearLayoutManager(this.context)
        adapter = SearchMovieAdapter()
        binding.searchRecyclerViewContainer.adapter = adapter
        addListOfMovies()
    }

    private fun addListOfMovies() {
        // Set the HashMap up with only the saved movies
        viewModel.getSavedMovies().observe(viewLifecycleOwner, Observer {
            viewModel.savedHash.clear()
            it.forEach { movie ->
                viewModel.savedHash[movie.id] = movie.saved
            }
        })

        // Add all of the movie objects into the Recycler View
        viewModel.getMovies().observe(viewLifecycleOwner, Observer {
            it.forEach { movie ->
                if (viewModel.savedHash.containsKey(movie.id))
                    movie.saved = true
            }
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Load state if it exists to preserve Recycler View position
        if (viewModel.listState != null) {
            binding.searchRecyclerViewContainer.layoutManager?.onRestoreInstanceState(viewModel.listState)
            viewModel.listState = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Save current state before being destroyed
        viewModel.listState =
            binding.searchRecyclerViewContainer.layoutManager?.onSaveInstanceState()
    }
}