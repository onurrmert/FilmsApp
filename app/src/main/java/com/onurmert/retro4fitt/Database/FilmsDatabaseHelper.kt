package com.onurmert.retro4fitt.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.onurmert.retro4fitt.Model.FilmsModel
import com.onurmert.retro4fitt.Model2.MovieModel1
import com.onurmert.retro4fitt.Model2.MovieModel2

class FilmsDatabaseHelper (context: Context) : SQLiteOpenHelper(context, databaseName, null, version){

    companion object{
        private val databaseName = "films.db"
        private val version = 1//must be version > 0
        private val tableName ="film"
        private val idKey = "id"
        private val titleKey = "title"
        private val yearKey = "year"
        private val ratingKey = "rating"
        private val summaryKey = "summary"
        private val large_cover_image_key = "large_cover_image"
        private val urlKey = "url"
    }
    override fun onCreate(sqlLite: SQLiteDatabase?) {
        val createTable = "CREATE TABLE IF NOT EXISTS ${tableName} ( ${idKey} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ${titleKey} TEXT, ${yearKey} TEXT, ${ratingKey} TEXT, ${summaryKey} TEXT, ${large_cover_image_key} TEXT, " +
                " ${urlKey} TEXT)"
        try {
            sqlLite!!.execSQL(createTable)
            println("create table")
        }catch (e: Exception){
            println("db create error")
            println(e.localizedMessage)
        }
    }

    override fun onUpgrade(sqlLite: SQLiteDatabase?, p1: Int, p2: Int) {
        sqlLite!!.execSQL("DROP TABLE IF EXISTS ${tableName}")
        onCreate(sqlLite)
    }

    fun insertFilms(filmsList : List<FilmsModel>){

        val contentValue = ContentValues()

        val sqLiteDatabase = this.writableDatabase

        filmsList.forEach {
            contentValue.put(idKey, it.id)
            contentValue.put(titleKey, it.title)
            contentValue.put(yearKey, it.year)
            contentValue.put(ratingKey, it.rating)
            contentValue.put(summaryKey, it.summary)
            contentValue.put(large_cover_image_key, it.large_cover_image)
            contentValue.put(urlKey, it.urlFilm)

            try {
                sqLiteDatabase.insert(tableName, null, contentValue)
            }catch (e : Exception){
                println("save error")
                println(e.localizedMessage)
            }
        }
        sqLiteDatabase.close()
    }

    @SuppressLint("Recycle")
    fun getAllFilms() : ArrayList<FilmsModel>{

        val filmsList = ArrayList<FilmsModel>()

        val sqLiteDatabase = this.readableDatabase

        val sqlString = "SELECT * FROM ${tableName} "

        val cursor = sqLiteDatabase.rawQuery(sqlString, null)
        try {
            val idIndex = cursor.getColumnIndex(idKey)
            val titleIndex = cursor.getColumnIndex(titleKey)
            val yearIndex = cursor.getColumnIndex(yearKey)
            val ratingIndex = cursor.getColumnIndex(ratingKey)
            val summaryIndex = cursor.getColumnIndex(summaryKey)
            val imageIndex = cursor.getColumnIndex(large_cover_image_key)
            val urlIndex = cursor.getColumnIndex(urlKey)

            if (cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(idIndex)
                    val title = cursor.getString(titleIndex)
                    val year = cursor.getString(yearIndex)
                    val rating = cursor.getString(ratingIndex)
                    val summary = cursor.getString(summaryIndex)
                    val image = cursor.getString(imageIndex)
                    val url = cursor.getString(urlIndex)

                    filmsList.add(FilmsModel(id,title,year,rating,summary,image,url))
                }
            }
        }catch (e : Exception){
            println("getAllfilms error" + e.localizedMessage)
        }finally {
            sqLiteDatabase.close()
            cursor.close()
        }
        return filmsList
    }

    fun getOnlyOneFilm(id : Int) : FilmsModel?{

        val sqLiteDatabase = this.readableDatabase

        val sqlString = "SELECT * FROM ${tableName} WHERE ${idKey} = ${id} "

        val cursor = sqLiteDatabase.rawQuery(sqlString, null)

        try {

            val idIndex = cursor.getColumnIndex(idKey)
            val titleIndex = cursor.getColumnIndex(titleKey)
            val yearIndex = cursor.getColumnIndex(yearKey)
            val ratingIndex = cursor.getColumnIndex(ratingKey)
            val summaryIndex = cursor.getColumnIndex(summaryKey)
            val imageIndex = cursor.getColumnIndex(large_cover_image_key)
            val urlIndex = cursor.getColumnIndex(urlKey)

            if (cursor.count > 0){
                while (cursor.moveToNext()){
                    val idd = cursor.getInt(idIndex)
                    val title = cursor.getString(titleIndex)
                    val year = cursor.getString(yearIndex)
                    val rating = cursor.getString(ratingIndex)
                    val summary = cursor.getString(summaryIndex)
                    val image = cursor.getString(imageIndex)
                    val url = cursor.getString(urlIndex)

                    return FilmsModel(idd, title, year, rating, summary, image, url)
                }
            }
        }catch (e : Exception){
            println("get one film error")
            println(e.localizedMessage)
        }finally {
            sqLiteDatabase.close()
            cursor.close()
        }
        return null
    }
}