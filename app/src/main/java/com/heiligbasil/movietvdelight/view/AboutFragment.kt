package com.heiligbasil.movietvdelight.view


import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.heiligbasil.movietvdelight.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Start the pulsating animation of the gradient
        val animatedBackground = fragment_about_parent_layout.background as AnimationDrawable
        animatedBackground.setEnterFadeDuration(2000)
        animatedBackground.setExitFadeDuration(3000)
        animatedBackground.start()
    }
}