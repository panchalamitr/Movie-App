package com.panchalamitr.oxforddictionary.repository

import com.panchalamitr.oxforddictionary.model.Movie
import com.panchalamitr.oxforddictionary.model.MovieDetail
import com.panchalamitr.oxforddictionary.service.MovieService
import com.panchalamitr.oxforddictionary.util.Constant
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(private val movieService: MovieService){

    suspend fun getMovieDetail(imdbId: String): MovieDetail {
        return movieService.getMovieDetail(imdbId, Constant.apiKey)
    }
}