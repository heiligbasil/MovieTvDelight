package com.heiligbasil.movietvdelight.model.entities

import com.google.gson.annotations.SerializedName

data class TvNetwork(
    val id: Int,

    @SerializedName("logo_path")
    val logoPath: String,

    val name: String,

    @SerializedName("origin_country")
    val originCountry: String
)