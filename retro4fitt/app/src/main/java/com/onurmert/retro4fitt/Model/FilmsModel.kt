package com.onurmert.retro4fitt.Model

import com.google.gson.annotations.SerializedName

data class FilmsModel(
    @SerializedName("id")
    val id : Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("year")
    val year: String? = null,

    @SerializedName("rating")
    val rating: String? = null,

    @SerializedName("summary")
    val summary: String? = null,

    @SerializedName("large_cover_image")
    val large_cover_image: String? = null,

    @SerializedName("url")
    val urlFilm : String? = null
)