package com.heiligbasil.movietvdelight.view

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.FragmentDetailsBinding
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.local.MovieDatabase
import com.heiligbasil.movietvdelight.model.remote.RemoteRepository
import com.heiligbasil.movietvdelight.viewmodel.*


class DetailsFragment : OptionsMenuFragment() {

    private val safeArgs: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var browseViewModel: BrowseViewModel
    private lateinit var savedViewModel: SavedViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var movie: MovieEssentials
    private lateinit var activeViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate the ViewModel from the other fragment in order to save changes
        val dao = MovieDatabase.getInstance(view.context).movieDao
        val localRepository = LocalRepository(dao)
        val remoteRepository = RemoteRepository(viewLifecycleOwner)
        val browseFactory = BrowseViewModelFactory(localRepository, remoteRepository)
        val searchFactory = SearchViewModelFactory(localRepository, remoteRepository)
        val savedFactory = SavedViewModelFactory(localRepository)
        browseViewModel = ViewModelProvider(this, browseFactory).get(BrowseViewModel::class.java)
        searchViewModel = ViewModelProvider(this, searchFactory).get(SearchViewModel::class.java)
        savedViewModel = ViewModelProvider(this, savedFactory).get(SavedViewModel::class.java)

        // Access the movie object passed in from the previous fragment
        movie = safeArgs.movieObject

        // Determine which ViewModel to use
        activeViewModel = safeArgs.viewModelEnum

        // Bind the movie object to the layout
        binding.movie = safeArgs.movieObject
binding.executePendingBindings()
        // Set the initial look of the Floating Action Button
        setFabVisuals()

        // Use the Floating Action Button as a saved indicator and toggler
        binding.fab.setOnClickListener {
            toggleFabVisuals()
        }

        // Change the Action Bar title and add an 'up' button on it
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar?.title = "${appCompatActivity.title} - Details"
    }

    /**
     * Toggle the saved attribute on the movie object and update the Floating Action Button's visuals
     */
    private fun toggleFabVisuals() {
        // Toggle the saved flag on the movie object
        movie.saved = !movie.saved
        setFabVisuals()

        // Commit the changes to the local database
        when (activeViewModel) {
            ViewModel.BROWSE -> browseViewModel.updateMovie(movie)
            ViewModel.SEARCH -> searchViewModel.updateMovie(movie)
            ViewModel.SAVED -> savedViewModel.updateMovie(movie)
        }
    }

    /**
     * Set the correct visual look for the Floating Action Button depending on the movie's saved flag
     */
    private fun setFabVisuals() {
        val icon = context?.let {
            if (movie.saved)
                ContextCompat.getDrawable(it, R.drawable.ic_saved)
            else
                ContextCompat.getDrawable(it, R.drawable.ic_save)
        }

        binding.fab.setImageDrawable(icon)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // Treat the Up button on the Action Bar the same as the Back button
            findNavController().popBackStack()
        }

        return super.onOptionsItemSelected(item)
    }
}