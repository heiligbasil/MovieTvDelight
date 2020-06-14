package com.heiligbasil.movietvdelight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SavedFragment : OptionsMenuFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.saved_fragment, container, false)
    }

}