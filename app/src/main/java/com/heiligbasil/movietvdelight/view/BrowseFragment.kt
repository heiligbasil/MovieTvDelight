package com.heiligbasil.movietvdelight.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.FragmentBrowseBinding
import com.heiligbasil.movietvdelight.model.BrowseRepository
import com.heiligbasil.movietvdelight.model.entities.MovieTopRated
import com.heiligbasil.movietvdelight.model.entities.MovieTopRatedResult
import com.heiligbasil.movietvdelight.model.remote.Retrofit
import com.heiligbasil.movietvdelight.model.remote.MtdService
import com.heiligbasil.movietvdelight.viewmodel.BrowseViewModel
import com.heiligbasil.movietvdelight.viewmodel.BrowseViewModelFactory
import kotlinx.android.synthetic.main.fragment_browse.*
import retrofit2.Response

class BrowseFragment : OptionsMenuFragment() {

    private lateinit var binding:FragmentBrowseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_browse,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository=BrowseRepository()
        val factory=BrowseViewModelFactory(repository)
        val viewModel=ViewModelProvider(this,factory).get(BrowseViewModel::class.java)
        binding.browseViewModel=viewModel
        binding.lifecycleOwner=this
        binding.browseRecyclerViewContainer.layoutManager=LinearLayoutManager(view.context)
        val adapter=BrowseMovieAdapter{viewModel.init(it)}
        binding.browseRecyclerViewContainer.adapter=adapter

        browse_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                onTabSelected(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {

                val tmdbService = Retrofit.getInstance().create(MtdService::class.java)

                if (tab?.text.toString().equals(getString(R.string.browse_top_rated_movies))) {

                    val mtrResponse = liveData<Response<MovieTopRated>> {
                        val response = tmdbService.getTopRatedMovies(Retrofit.apiKey, "enUS", 1)
                        emit(response)
                    }

                    mtrResponse.observe(viewLifecycleOwner, Observer {
                        val mtr = it.body()
                        val topRatedMoviesIterator = mtr?.results?.listIterator()
                        val topRatedMovies=ArrayList<MovieTopRatedResult>()
                        if (topRatedMoviesIterator !== null) {
                            while (topRatedMoviesIterator.hasNext()) {
                                val topRatedMovie = topRatedMoviesIterator.next()
                                topRatedMovies.add(topRatedMovie)
                            }
                            adapter.setList(topRatedMovies)
                            adapter.notifyDataSetChanged()
                        }
                    })
                } else {

                }
            }

        })

        browse_tab_layout.selectTab(browse_tab_layout.getTabAt(0))

    }

}