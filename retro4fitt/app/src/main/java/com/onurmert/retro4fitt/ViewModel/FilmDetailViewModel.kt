package com.onurmert.retro4fitt.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurmert.retro4fitt.Database.FilmsDatabaseHelper
import com.onurmert.retro4fitt.Model.FilmsModel

class FilmDetailViewModel : ViewModel(){

    val filmModel = MutableLiveData<FilmsModel>()

    fun getOnlyFilm(context : Context, id : Int){

        val sqLiteDatabase = FilmsDatabaseHelper(context)

        filmModel.value = sqLiteDatabase.getOnlyOneFilm(id)
    }
}