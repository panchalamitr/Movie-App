package com.panchalamitr.oxforddictionary.repository

import com.panchalamitr.oxforddictionary.data.local.MovieDao
import com.panchalamitr.oxforddictionary.model.MovieDetail
import com.panchalamitr.oxforddictionary.data.remote.MovieService
import com.panchalamitr.oxforddictionary.util.Constant
import timber.log.Timber
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(private val movieDao: MovieDao, private val movieService: MovieService){

    suspend fun getMovieDetail(imdbId: String): MovieDetail {
        /** As per requirement we will fetch data from the database first **/
        val movieDetailDB = movieDao.getMovieDetail(imdbId)

        /** If record found then we will return that records **/
        if(movieDetailDB != null){
            Timber.d("Movie Detail Found From DB")
            return movieDetailDB
        }

        /**
         *  If no record found from database then we will call webservice.
         *  Now we will store data which we will get from webservice
         *  Now we will return data
         */
        val movieDetail = movieService.getMovieDetail(imdbId, Constant.apiKey)
        movieDao.insertMovieDetail(movieDetail)

        Timber.d("Movie List")
        return movieDetail
    }
}