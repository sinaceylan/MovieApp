package com.sina.navigationkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sina.navigationkotlin.models.Movie
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter(
    private val movies: List<Movie>,
    private val listener: OnItemClickListener

) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val IMAGE_BASE ="https://image.tmdb.org/t/p/w500/"

        fun bindMovie(movie: Movie, listener: OnItemClickListener){
           // itemView.movie_title.text = movie.title
           // itemView.movie_release_date.text = movie.release
           // itemView.vote_average.text = movie.average.toString()

            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_poster)

            itemView.setOnClickListener{ listener.onItemClick(movie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position),listener)

    }

    override fun getItemCount(): Int = movies.size
}

