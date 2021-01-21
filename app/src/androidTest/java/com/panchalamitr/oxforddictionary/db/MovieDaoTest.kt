package com.panchalamitr.oxforddictionary.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.panchalamitr.oxforddictionary.model.MovieDetail
import com.panchalamitr.oxforddictionary.model.Search
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieDaoTest {

    private lateinit var database: MovieDatabase
    private lateinit var dao: MovieDao

    /** It will call before every test cases executed **/
    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getMovieDao()
    }

    /** Test movie is inserted successfully **/
    @Test
    fun insertMovie() = runBlockingTest {
        val movie = Search(
            title = "Title",
            year = "19-10-2020",
            poster = "http://www.google.com",
            type = "Movie",
            imdbID = "tt123123"
        )
        dao.insertMovie(movie)

        val allMovies = dao.getAllMovie()

        Truth.assertThat(allMovies).contains(movie)
    }

    /** Test movieDetail inserted successfully **/
    @Test
    fun insertMovieDetail() = runBlockingTest {
        val movieDetail = MovieDetail(
            title = "Title",
            year = "19-10-2020",
            poster = "http://www.google.com",
            type = "Movie",
            imdbID = "tt123123",
            rated = "5",
            released = "1990",
            runtime = "Yes",
            genre = "Fight",
            director = "Ak Chopra",
            writer = "Amit",
            actors = "Sushant",
            language = "English",
            metascore = "Score",
            dVD = "Rpi",
            awards = "Goldn Globe",
            imdbRating = "5"
        )

        dao.insertMovieDetail(movieDetail)

        val movieDetailOb = dao.getMovieDetail("tt123123")

        Truth.assertThat(movieDetailOb).isNotNull()
    }

    /** It will call after every test cases **/
    @After
    fun tearDown(){
        database.close()
    }
}