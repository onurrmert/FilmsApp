package com.onurmert.retro4fitt.Model

import com.google.gson.annotations.SerializedName

data class DataFilms(
    @SerializedName("movies")
    val movies: List<FilmsModel>?,
    @SerializedName("page_number")
    val pageNumber: Int = 0,
    @SerializedName("movie_count")
    val movieCount: Int = 0,
    @SerializedName("limit")
    val limit: Int = 0
)