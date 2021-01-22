package com.sstudio.pagingwithretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_photos.view.*


class MoviesAdapter : PagedListAdapter<Movie.Result, MoviesAdapter.MovieViewHolder?>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie.Result> = object : DiffUtil.ItemCallback<Movie.Result>() {
            override fun areItemsTheSame(movie: Movie.Result, t1: Movie.Result): Boolean {
                return movie.id == t1.id
            }

            override fun areContentsTheSame(Movie: Movie.Result, t1: Movie.Result): Boolean {
                return true
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater.inflate(R.layout.list_photos, viewGroup, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movieViewHolder.bind(it) }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie.Result) {
//        val url = GlideUrl(
//            getItem(i)?.url, LazyHeaders.Builder()
//                .addHeader("User-Agent", "your-user-agent")
//                .build()
//        )
            with(itemView) {
                Glide.with(context)
                    .load(BuildConfig.POSTER_THUMBNAIL + movie.poster_path)
                    .into(imageView)
            }
        }
    }
}