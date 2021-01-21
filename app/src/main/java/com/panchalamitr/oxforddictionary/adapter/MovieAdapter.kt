package com.panchalamitr.oxforddictionary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.panchalamitr.oxforddictionary.R
import com.panchalamitr.oxforddictionary.interfaces.ItemClickListener
import com.panchalamitr.oxforddictionary.model.Movie
import timber.log.Timber

class MovieAdapter(
    private val mContext: Context,
    private var movieResult: Movie,
    private var onItemClickListener: ItemClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieResult!!.search!![position]
        movie.apply {
            holder.mTitleView.text = "$title (${movie.type!!.capitalize()})"
            holder.mYearView.text = "Year: $year"
            val imageUrl = if (poster != "N/A") {
                poster
            } else {
                // default image if there is no poster available
                mContext.resources.getString(R.string.default_poster)
            }
            Glide.with(mContext).load(imageUrl).into(holder.mThumbImageView)
            holder.mView.tag = movie.imdbID
            holder.mView.setOnClickListener {
                val imdbID = it.tag as String
                onItemClickListener.onItemClick(imdbID)
            }
        }
    }

    override fun getItemCount(): Int {
        val total = if (movieResult!!.search == null) {
            0
        } else movieResult!!.search!!.size
        Timber.d("Total $total")
        return total
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val mTitleView: TextView = mView.findViewById<TextView>(R.id.tvMovieTitle)
        val mYearView: TextView = mView.findViewById<TextView>(R.id.tvMovieYear)
        val mThumbImageView: ImageView = mView.findViewById<ImageView>(R.id.ivThumbnail)
    }

}