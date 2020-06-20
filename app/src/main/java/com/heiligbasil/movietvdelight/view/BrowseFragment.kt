package com.heiligbasil.movietvdelight.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.FragmentBrowseBinding
import com.heiligbasil.movietvdelight.model.entities.Utils
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.local.MovieDatabase
import com.heiligbasil.movietvdelight.model.remote.RemoteRepository
import com.heiligbasil.movietvdelight.viewmodel.BrowseViewModel
import com.heiligbasil.movietvdelight.viewmodel.BrowseViewModelFactory
import kotlinx.android.synthetic.main.fragment_browse.*

class BrowseFragment : OptionsMenuFragment() {

    private lateinit var binding: FragmentBrowseBinding
    private lateinit var viewModel: BrowseViewModel
    private lateinit var adapter: BrowseMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_browse, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = MovieDatabase.getInstance(view.context).movieDao
        val localRepository = LocalRepository(dao)
        val remoteRepository = RemoteRepository(viewLifecycleOwner)
        val factory = BrowseViewModelFactory(localRepository, remoteRepository)
        viewModel = ViewModelProvider(this, factory).get(BrowseViewModel::class.java)
        binding.browseViewModel = viewModel
        binding.lifecycleOwner = this

        browse_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                onTabSelected(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text.toString() == getString(R.string.browse_top_rated_movies)) {
                    viewModel.setTabChoice(0)
                } else {
                    viewModel.setTabChoice(1)
                }
                initRecyclerView()
                viewModel.storeMovies()
            }
        })

        // Ensure a good tab state upon creation
        browse_tab_layout.selectTab(browse_tab_layout.getTabAt(viewModel.selectedTabPosition))

        // Re-initialize the Recycler View when the view is swiped
        browse_swipe_refresh_layout.setOnRefreshListener {
            initRecyclerView()
        }

        // Retrieve all of the local database objects and store them in an accessible format
        viewModel.getLocalMovies().observe(viewLifecycleOwner, Observer {
            viewModel.dbList.clear()
            it.forEach { movie ->
                viewModel.dbList.add(movie)
            }
        })

        // Save the current location using Shared Preferences
        Utils.saveLocation(view.context, 0)
    }

    private fun initRecyclerView() {
        binding.browseRecyclerViewContainer.layoutManager = LinearLayoutManager(this.context)
        adapter = BrowseMovieAdapter()
        binding.browseRecyclerViewContainer.adapter = adapter
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

            // Turn off the spinning loading icon
            browse_swipe_refresh_layout.isRefreshing = false
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Load state if it exists to preserve Recycler View position
        if (viewModel.listState != null) {
            binding.browseRecyclerViewContainer.layoutManager?.onRestoreInstanceState(viewModel.listState)
            viewModel.listState = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Save current state before being destroyed
        viewModel.listState =
            binding.browseRecyclerViewContainer.layoutManager?.onSaveInstanceState()
    }
}
