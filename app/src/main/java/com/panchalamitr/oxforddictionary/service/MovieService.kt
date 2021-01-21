package com.panchalamitr.oxforddictionary.service

import com.panchalamitr.oxforddictionary.model.Movie
import com.panchalamitr.oxforddictionary.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {


    @GET(".")
    suspend fun getMovie(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String,
        @Query("s") seach: String
    ): Movie

    @GET("?plot=full")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): MovieDetail
}