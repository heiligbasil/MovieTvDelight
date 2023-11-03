package com.heiligbasil.movietvdelight.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.heiligbasil.movietvdelight.R
import com.heiligbasil.movietvdelight.databinding.ActivityMainBinding
import com.heiligbasil.movietvdelight.model.entities.Utils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(this.layoutInflater)
        val view = binding.root
        setTheme(R.style.AppTheme)
        setContentView(view)

        val navController = findNavController(R.id.nav_host_fragment)
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            .setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_about || destination.id == R.id.nav_details) {
                binding.bottomNavigationView.visibility = View.GONE
            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }

        // Load and go to the previous location from Shared Preferences
        loadPreviousLocation(navController)
    }

    private fun loadPreviousLocation(navController: NavController) {
        val layoutDestination = when (Utils.loadLocation(this)) {
            1 -> R.id.nav_search
            2 -> R.id.nav_saved
            else -> R.id.nav_browse
        }
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navGraph.setStartDestination(layoutDestination)
        navController.graph = navGraph
    }
}