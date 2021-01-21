package com.panchalamitr.oxforddictionary.repository

import com.panchalamitr.oxforddictionary.db.MovieDao
import com.panchalamitr.oxforddictionary.model.MovieDetail
import com.panchalamitr.oxforddictionary.service.MovieService
import com.panchalamitr.oxforddictionary.util.Constant
import timber.log.Timber
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(private val movieDao: MovieDao, private val movieService: MovieService){

    suspend fun getMovieDetail(imdbId: String): MovieDetail {

        val movieDetailDB = movieDao.getMovieDetail(imdbId)

        if(movieDetailDB != null){
            Timber.d("Movie Detail Found From DB")
            return movieDetailDB
        }

        val movieDetail = movieService.getMovieDetail(imdbId, Constant.apiKey)
        movieDao.insertMovieDetail(movieDetail)

        Timber.d("Movie List")
        return movieDetail
    }
}