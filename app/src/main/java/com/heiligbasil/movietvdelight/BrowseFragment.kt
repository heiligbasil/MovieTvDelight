package com.heiligbasil.movietvdelight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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

    }

}