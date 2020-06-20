package com.heiligbasil.movietvdelight.view

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.FragmentDetailsBinding
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.local.MovieDatabase
import com.heiligbasil.movietvdelight.model.remote.RemoteRepository
import com.heiligbasil.movietvdelight.viewmodel.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_details.*


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
//        activity?.setTheme(R.style.AppTheme_NoActionBar)
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
        binding.movie = movie

        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar?.title=movie.title
        binding.toolbarLayout.title=movie.title

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        // Show the menu item and toggle the checked state to match the movie record
        menu[1].apply {
            this.isVisible = true
            this.isChecked = movie.saved
            this.icon = context?.let {
                if (this.isChecked)
                    ContextCompat.getDrawable(it, R.drawable.ic_saved)
                else
                    ContextCompat.getDrawable(it, R.drawable.ic_save)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title == getString(R.string.options_menu_saved)) {
            item.icon = context?.let {
                // Toggle the checked state and visible icon
                if (item.isChecked) {
                    item.isChecked = false
                    ContextCompat.getDrawable(it, R.drawable.ic_save)
                } else {
                    item.isChecked = true
                    ContextCompat.getDrawable(it, R.drawable.ic_saved)
                }
            }

            // Update the saved attribute and commit the changes to the local database
            movie.saved = item.isChecked
            when (activeViewModel) {
                ViewModel.BROWSE -> browseViewModel.updateMovie(movie)
                ViewModel.SEARCH -> searchViewModel.updateMovie(movie)
                ViewModel.SAVED -> savedViewModel.updateMovie(movie)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}