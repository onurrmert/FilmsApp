package com.onurmert.retro4fitt.Retrofit1

import android.content.Context
import com.google.gson.GsonBuilder
import com.onurmert.retro4fitt.Database.FilmsDatabaseHelper
import com.onurmert.retro4fitt.Model.Films
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient (){

    private val BASE_URL = "https://yts.mx/api/v2/"

    private lateinit var filmsDatabaseHelper : FilmsDatabaseHelper

    private fun getApiClient() : Retrofit{

        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

    fun getMovie(context: Context){

        try {
            filmsDatabaseHelper = FilmsDatabaseHelper(context)
        }catch (e: Exception){
            println("error db apiClient: " + e.localizedMessage)
        }
        val apiClient = ApiClient().getApiClient().create(IFilmsApi::class.java)

        apiClient.getMovieApi().enqueue(object : Callback<Films> {
            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        try {
                            filmsDatabaseHelper.insertFilms(response.body()!!.data.movies!!)
                        }catch (e :Exception){
                            println("error: " + e.localizedMessage)
                        }
                    }else{
                        println("error body null")
                    }
                }else{
                    println("error isSuccessful")
                }
            }
            override fun onFailure(call: Call<Films>, t: Throwable) {
                println("failure error")
            }
        })
    }
}
