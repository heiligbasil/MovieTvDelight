package com.heiligbasil.movietvdelight

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
            .setupWithNavController(navController)

    }

}