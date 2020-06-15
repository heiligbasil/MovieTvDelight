package com.heiligbasil.movietvdelight.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.google.android.material.tabs.TabLayout
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.model.entities.MovieTopRated
import com.heiligbasil.movietvdelight.model.remote.Retrofit
import com.heiligbasil.movietvdelight.model.remote.MtdService
import kotlinx.android.synthetic.main.fragment_browse.*
import retrofit2.Response

class BrowseFragment : OptionsMenuFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_browse, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                        val topRatedMovies = mtr?.results?.listIterator()
                        if (topRatedMovies !== null) {
                            while (topRatedMovies.hasNext()) {
                                val topRatedMovie = topRatedMovies.next()
                                Log.i("test321", topRatedMovie.title)
                            }
                        }
                    })
                } else {

                }
            }

        })

        browse_tab_layout.selectTab(browse_tab_layout.getTabAt(0))

    }

}