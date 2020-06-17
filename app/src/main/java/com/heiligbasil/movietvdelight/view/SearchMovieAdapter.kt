package com.heiligbasil.movietvdelight.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.FragmentSearchItemBinding
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.viewmodel.ViewModel

class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder>() {

    private val movieList = ArrayList<MovieEssentials>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FragmentSearchItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search_item, parent, false)
        return SearchMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun setList(movies: List<MovieEssentials>) {
        movieList.clear()
        movieList.addAll(movies)
    }

    override fun getItemCount(): Int = movieList.size

    inner class SearchMovieViewHolder(private val binding: FragmentSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEssentials) {
            binding.movie = movie
            binding.root.setOnClickListener {
                val args = DetailsFragmentArgs(movie, ViewModel.SEARCH).toBundle()
                it.findNavController().navigate(R.id.nav_details, args)
            }
        }
    }
}