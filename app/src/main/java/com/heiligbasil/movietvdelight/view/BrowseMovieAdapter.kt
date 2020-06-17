package com.heiligbasil.movietvdelight.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.FragmentBrowseItemBinding
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.viewmodel.ViewModel

class BrowseMovieAdapter(private val clickListener: (MovieEssentials) -> Unit) :
    RecyclerView.Adapter<BrowseMovieAdapter.BrowseMovieViewHolder>() {

    private val movieList = ArrayList<MovieEssentials>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrowseMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FragmentBrowseItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_browse_item, parent, false)
        return BrowseMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrowseMovieViewHolder, position: Int) {
        holder.bind(movieList[position], clickListener)
    }

    fun setList(movies: List<MovieEssentials>) {
        movieList.clear()
        movieList.addAll(movies)
    }

    override fun getItemCount(): Int = movieList.size

    inner class BrowseMovieViewHolder(private val binding: FragmentBrowseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEssentials, clickListener: (MovieEssentials) -> Unit) {
            binding.movie = movie
            binding.root.setOnClickListener {
                val args = DetailsFragmentArgs(movie, ViewModel.BROWSE).toBundle()
                it.findNavController().navigate(R.id.nav_details, args)
            }
        }
    }
}