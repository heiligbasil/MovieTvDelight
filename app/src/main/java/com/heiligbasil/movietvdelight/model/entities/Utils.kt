package com.heiligbasil.movietvdelight.model.entities

import android.content.Context

object Utils {
    fun saveLocation(context: Context, currentLocation: Int) {
        val preferences = context.getSharedPreferences("mtd_data", Context.MODE_PRIVATE)
        preferences.edit().putInt("location", currentLocation).apply()
    }

    fun loadLocation(context: Context): Int {
        val preferences = context.getSharedPreferences("mtd_data", Context.MODE_PRIVATE)

        return preferences.getInt("location", 0)
    }
}