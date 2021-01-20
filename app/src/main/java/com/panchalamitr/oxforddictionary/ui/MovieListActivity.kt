package com.panchalamitr.oxforddictionary.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.panchalamitr.oxforddictionary.R
import com.panchalamitr.oxforddictionary.adapter.MovieAdapter
import com.panchalamitr.oxforddictionary.databinding.ActivityMovieListBinding
import com.panchalamitr.oxforddictionary.interfaces.ItemClickListener
import com.panchalamitr.oxforddictionary.model.Movie
import com.panchalamitr.oxforddictionary.repository.MovieListRepository
import com.panchalamitr.oxforddictionary.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class MovieListActivity : AppCompatActivity(), ItemClickListener {


    @Inject
    lateinit var movieRepository: MovieListRepository

    private lateinit var activityMovieListBinding: ActivityMovieListBinding

    private lateinit var mMovieAdapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMovieListBinding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(activityMovieListBinding.root)
        setSupportActionBar(activityMovieListBinding.toolbar)

        val movieListViewModel: MovieListViewModel by viewModels()
        movieListViewModel.getMovie()

        movieListViewModel.observeMovieList().observe(this, {
            Timber.d("data Observed ${it.search}")
               initRecyclerView(it)
        })

        movieListViewModel.observeProgress().observe(this, {
            if(it) {
                activityMovieListBinding.progressSpinner.visibility = View.VISIBLE
            }else{
                activityMovieListBinding.progressSpinner.visibility = View.GONE
            }
        })

        movieListViewModel.observeErrorMessage().observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initRecyclerView(movie: Movie) {

        mMovieAdapter = MovieAdapter(this, movie, this)

        activityMovieListBinding.rvMovies.adapter = mMovieAdapter
        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        val gridLayoutManager = StaggeredGridLayoutManager(
            resources.getInteger(R.integer.grid_column_count),
            StaggeredGridLayoutManager.VERTICAL
        )

        activityMovieListBinding.rvMovies.itemAnimator = null
        // Attach the layout manager to the recycler view
        activityMovieListBinding.rvMovies.layoutManager = gridLayoutManager
    }

    override fun onItemClick(imdbId: String) {
        val movieDetailIntent = Intent(this, MovieDetailActivity::class.java)
        movieDetailIntent.putExtra("detail", imdbId)
        startActivity(movieDetailIntent)
    }

}