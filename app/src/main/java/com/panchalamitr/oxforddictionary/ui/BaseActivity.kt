package com.panchalamitr.oxforddictionary.ui

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    var mProgressBar: ProgressBar? = null

    fun initProgressBar(progressBar: ProgressBar?) {
        mProgressBar = progressBar
    }

    fun showProgressBar() {
        if (mProgressBar != null) {
            mProgressBar!!.visibility = View.VISIBLE
        }
    }

    fun hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar!!.visibility = View.INVISIBLE
        }
    }

    override fun onStop() {
        super.onStop()
        hideProgressBar()
    }

}