package com.onurmert.retro4fitt.Model

import com.google.gson.annotations.SerializedName

data class TorrentsModel(
    @SerializedName("size_bytes")
    val sizeBytes: Int = 0,
    @SerializedName("size")
    val size: String = "",
    @SerializedName("seeds")
    val seeds: Int = 0,
    @SerializedName("date_uploaded")
    val dateUploaded: String = "",
    @SerializedName("peers")
    val peers: Int = 0,
    @SerializedName("date_uploaded_unix")
    val dateUploadedUnix: Int = 0,
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("hash")
    val hash: String = "",
    @SerializedName("quality")
    val quality: String = ""
)