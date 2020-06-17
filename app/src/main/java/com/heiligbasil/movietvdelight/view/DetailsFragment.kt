package com.heiligbasil.movietvdelight.view

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
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
import com.heiligbasil.movietvdelight.viewmodel.BrowseViewModel
import com.heiligbasil.movietvdelight.viewmodel.BrowseViewModelFactory


class DetailsFragment : OptionsMenuFragment() {

    private val safeArgs: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: BrowseViewModel
    private lateinit var movie: MovieEssentials

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
        val factory = BrowseViewModelFactory(localRepository, remoteRepository)
        viewModel = ViewModelProvider(this, factory).get(BrowseViewModel::class.java)

        // Access the Movie object passed in from the previous fragment
        movie = safeArgs.sharedMovie

        // Bind the Movie object to the layout
        binding.movie = movie

//        ViewCompat.setTransitionName(fragment_details_image,"dada")
//        val title=args.sharedTitle
//        val transitionName = ViewCompat.getTransitionName(fragment_details_image).toString()
//        val stringImage = (arguments as Bundle).getString("img", "")
//        val stringTitle = (arguments as Bundle).getString("dodo", "")
//        val createImageUrl = Retrofit.buildPosterUrl(movie.posterPath,large = true)
//        Picasso.get().load(createImageUrl).into(fragment_details_image)
//        fragment_details_text_title.text="The Shawshank Redemption"
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
            viewModel.updateMovie(movie)
        }
        return super.onOptionsItemSelected(item)
    }
}