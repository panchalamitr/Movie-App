package com.panchalamitr.oxforddictionary.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.panchalamitr.oxforddictionary.model.MovieDetail
import com.panchalamitr.oxforddictionary.model.Search

@Dao
interface MovieDao {

    @Query("SELECT * FROM Search")
    fun getAllMovie(): List<Search>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie : Search)

    @Query("SELECT * FROM MovieDetail WHERE imdbID = :imdbId")
    fun getMovieDetail(imdbId: String): MovieDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movieDetail : MovieDetail)
}