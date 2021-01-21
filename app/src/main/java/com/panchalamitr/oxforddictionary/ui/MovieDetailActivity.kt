package com.panchalamitr.oxforddictionary.ui

import android.R
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.panchalamitr.oxforddictionary.databinding.ActivityMovieDetailBinding
import com.panchalamitr.oxforddictionary.repository.MovieDetailRepository
import com.panchalamitr.oxforddictionary.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MovieDetailActivity : BaseActivity() {

    @Inject
    lateinit var movieDetailRepository: MovieDetailRepository

    private lateinit var activityMovieDetailBinding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMovieDetailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(activityMovieDetailBinding.root)
        setSupportActionBar(activityMovieDetailBinding.toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.setDisplayShowHomeEnabled(true);

        initProgressBar(activityMovieDetailBinding.pbLoading)


        activityMovieDetailBinding.toolbar.title = "Movie Detail"
        val imdiId = intent.getStringExtra("detail")

        val movieDetailViewModel: MovieDetailViewModel by viewModels()
        movieDetailViewModel.getMovieDetail(imdiId!!)

        movieDetailViewModel.observeMovieDetail().observe(this, {
            activityMovieDetailBinding.apply {
                gridActors.text = it.actors
                gridDirector.text = it.director
                gridGenre.text = it.genre
                gridPlot.text = it.plot
                gridReleased.text = it.released
                gridRuntime.text = it.runtime
                gridWriters.text = it.writer
                gridTitle.text = it.title
                Glide.with(this@MovieDetailActivity).load(it.poster).into(ivMoviePoster)
            }
        })

        movieDetailViewModel.observeProgress().observe(this, {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        })

        movieDetailViewModel.observeErrorMessage().observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() === R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

}