package com.heiligbasil.movietvdelight.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.FragmentSavedItemBinding
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.viewmodel.ViewModel

class SavedMovieAdapter : RecyclerView.Adapter<SavedMovieAdapter.SavedMovieViewHolder>() {

    private val movieList = ArrayList<MovieEssentials>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FragmentSavedItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_saved_item, parent, false)
        return SavedMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedMovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun setList(movies: List<MovieEssentials>) {
        movieList.clear()
        movieList.addAll(movies)
    }

    override fun getItemCount(): Int = movieList.size

    inner class SavedMovieViewHolder(private val binding: FragmentSavedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEssentials) {
            binding.movie = movie
            binding.root.setOnClickListener {
                val args = DetailsFragmentArgs(movie, ViewModel.SAVED).toBundle()
                it.findNavController().navigate(R.id.nav_details, args)
            }
        }
    }
}