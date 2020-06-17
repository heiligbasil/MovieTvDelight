package com.heiligbasil.movietvdelight.model.entities

import com.google.gson.annotations.SerializedName


data class MovieSpokenLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String,

    val name: String
)