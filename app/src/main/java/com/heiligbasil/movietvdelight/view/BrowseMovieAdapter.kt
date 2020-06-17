package com.heiligbasil.movietvdelight.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.FragmentBrowseItemBinding
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.viewmodel.ViewModel

class BrowseMovieAdapter :
    RecyclerView.Adapter<BrowseMovieAdapter.BrowseMovieViewHolder>() {

    private val movieList = ArrayList<MovieEssentials>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrowseMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FragmentBrowseItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_browse_item, parent, false)
        return BrowseMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrowseMovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun setList(movies: List<MovieEssentials>) {
        movieList.clear()
        movieList.addAll(movies)
    }

    override fun getItemCount(): Int = movieList.size

    inner class BrowseMovieViewHolder(private val binding: FragmentBrowseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEssentials) {
            binding.movie = movie
            binding.root.setOnClickListener {
                val args = DetailsFragmentArgs(movie, ViewModel.BROWSE).toBundle()
                it.findNavController().navigate(R.id.nav_details, args)
            }
        }
    }
}