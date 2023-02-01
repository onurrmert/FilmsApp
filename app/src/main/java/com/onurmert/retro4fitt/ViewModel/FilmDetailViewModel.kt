package com.onurmert.retro4fitt.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurmert.retro4fitt.Database.FilmsDatabaseHelper
import com.onurmert.retro4fitt.Model.FilmsModel
import com.onurmert.retro4fitt.Model2.MovieModel1

class FilmDetailViewModel : ViewModel(){

    val movieModel = MutableLiveData<MovieModel1>()

    fun getOnlyFilm(context : Context, id : Int){

        val sqLiteDatabase = FilmsDatabaseHelper(context)

        movieModel.value = sqLiteDatabase.getOnlyOneFilm(id)
    }
}