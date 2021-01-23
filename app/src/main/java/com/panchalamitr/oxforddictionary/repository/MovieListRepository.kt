package com.panchalamitr.oxforddictionary.repository

import com.panchalamitr.oxforddictionary.data.local.MovieDao
import com.panchalamitr.oxforddictionary.model.Movie
import com.panchalamitr.oxforddictionary.data.remote.MovieService
import com.panchalamitr.oxforddictionary.util.Constant
import timber.log.Timber
import javax.inject.Inject

class MovieListRepository @Inject constructor(private val movieDao: MovieDao, private val movieService: MovieService){


    suspend fun getMovie(): Movie {
        /** As per requirement we will fetch data from the database first **/
        val moviesList = movieDao.getAllMovie()

        /** If record found then we will return that records **/
        if(moviesList != null && moviesList.isNotEmpty()){
            Timber.d("movieList is found in DB")
            return Movie(moviesList)
        }

        /**
         *  If no record found from database then we will call webservice.
         *  Now we will store data which we will get from webservice
         *  Now we will return data
         */
        val movie = movieService.getMovie(Constant.ibdbId, Constant.apiKey, "Iron")
        for(search in movie.search!!){
            movieDao.insertMovie(search)
        }
        Timber.d("Movie List")
        return movie
    }
}