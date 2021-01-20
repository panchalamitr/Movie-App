package com.panchalamitr.oxforddictionary.repository

import com.panchalamitr.oxforddictionary.model.Movie
import com.panchalamitr.oxforddictionary.service.MovieService
import com.panchalamitr.oxforddictionary.util.Constant
import javax.inject.Inject

class MovieListRepository @Inject constructor(private val movieService: MovieService){

    suspend fun getMovie(): Movie {
        return movieService.getMovie(Constant.ibdbId, Constant.apiKey, "Iron")
    }
}