package com.onurmert.retro4fitt.Retrofit1

import com.onurmert.retro4fitt.Model.Films
import retrofit2.Call
import retrofit2.http.GET

interface IFilmsApi {
    @GET("list_movies.json?sort_by=date_added")
    fun getMovieApi() : Call<Films>
}