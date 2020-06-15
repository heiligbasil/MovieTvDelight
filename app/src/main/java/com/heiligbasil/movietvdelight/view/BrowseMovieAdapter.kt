package com.heiligbasil.movietvdelight.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.FragmentBrowseItemBinding
import com.heiligbasil.movietvdelight.model.entities.MovieTopRatedResult
import com.heiligbasil.movietvdelight.model.remote.Retrofit
import com.squareup.picasso.Picasso

class BrowseMovieAdapter(private val clickListener: (MovieTopRatedResult) -> Unit) :
    RecyclerView.Adapter<BrowseMovieAdapter.BrowseMovieViewHolder>() {

    private val movieList = ArrayList<MovieTopRatedResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrowseMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: FragmentBrowseItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_browse_item, parent, false)
        return BrowseMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrowseMovieViewHolder, position: Int) {
        holder.bind(movieList[position], clickListener)
    }

    fun setList(movies: List<MovieTopRatedResult>) {
        movieList.clear()
        movieList.addAll(movies)
    }

    override fun getItemCount(): Int = movieList.size

    inner class BrowseMovieViewHolder(private val binding: FragmentBrowseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieTopRatedResult, clickListener: (MovieTopRatedResult) -> Unit) {
            val createImageUrl = Retrofit.buildPosterUrl(movie.posterPath)
            Picasso.get().load(createImageUrl).into(binding.browseRecyclerViewImage)
            binding.browseRecyclerViewTextTitle.text = movie.title
            binding.browseRecyclerViewTextYear.text = movie.releaseDate.substring(0..3)
            binding.browseRecyclerViewTextLanguage.text = movie.originalLanguage
            binding.browseRecyclerViewTextOverview.text = movie.overview
        }
    }
}