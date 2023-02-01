package com.onurmert.retro4fitt.Model

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("server_time")
    val serverTime: Int = 0,
    @SerializedName("server_timezone")
    val serverTimezone: String = "",
    @SerializedName("api_version")
    val apiVersion: Int = 0,
    @SerializedName("execution_time")
    val executionTime: String = ""
)