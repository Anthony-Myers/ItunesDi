package com.example.gm_challenge.model


import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Result(
    val artistName: String?,
    val primaryGenreName: String?,
    val releaseDate: Date?,
    val trackName: String?,
    val trackPrice: Double?,
)