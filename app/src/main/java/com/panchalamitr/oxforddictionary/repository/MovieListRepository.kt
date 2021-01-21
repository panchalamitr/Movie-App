package com.panchalamitr.oxforddictionary.repository

import com.panchalamitr.oxforddictionary.db.MovieDao
import com.panchalamitr.oxforddictionary.model.Movie
import com.panchalamitr.oxforddictionary.service.MovieService
import com.panchalamitr.oxforddictionary.util.Constant
import timber.log.Timber
import javax.inject.Inject

class MovieListRepository @Inject constructor(private val movieDao: MovieDao, private val movieService: MovieService){


    suspend fun getMovie(): Movie {

        val moviesList = movieDao.getAllMovie()

        if(moviesList != null && moviesList.isNotEmpty()){
            Timber.d("movieList is found in DB")
            return Movie(moviesList)
        }

        val movie = movieService.getMovie(Constant.ibdbId, Constant.apiKey, "Iron")
        for(search in movie.search!!){
            movieDao.insertMovie(search)
        }
        Timber.d("Movie List")
        return movie
    }
}