package com.heiligbasil.movietvdelight.model.entities

import com.google.gson.annotations.SerializedName

data class MovieProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String,

    val name: String
)