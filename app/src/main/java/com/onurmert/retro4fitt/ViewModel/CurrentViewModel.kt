package com.onurmert.retro4fitt.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurmert.retro4fitt.Database.FilmsDatabaseHelper
import com.onurmert.retro4fitt.Model.FilmsModel

class CurrentViewModel() : ViewModel() {

    val filmList = MutableLiveData<ArrayList<FilmsModel>>()

    fun getMovieViewModel(context: Context){

        val filmsDatabaseHelper = FilmsDatabaseHelper(context)

        filmList.value = filmsDatabaseHelper.getAllFilms()
    }
}