package com.panchalamitr.oxforddictionary.repository

import com.panchalamitr.oxforddictionary.model.Movie

class FakeMovieListRepository {

    suspend fun getMovie(): Movie {
        return Movie()
    }
}