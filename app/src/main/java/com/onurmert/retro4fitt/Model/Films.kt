package com.onurmert.retro4fitt.Model

import com.google.gson.annotations.SerializedName

data class Films(
    @SerializedName("status_message")
    val statusMessage: String = "",
    @SerializedName("data")
    val data: DataFilms,
    @SerializedName("@meta")
    val Meta: Meta,
    @SerializedName("status")
    val status: String = ""
)