package com.example.countriessimple.model

import com.squareup.moshi.Json

data class Country(
    @Json(name = "name")
    val countryName: String?,
    @Json(name = "flag")
    val imageUrl: String?
)
