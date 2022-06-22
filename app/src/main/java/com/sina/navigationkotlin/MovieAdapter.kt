package com.sina.navigationkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sina.navigationkotlin.models.Movie
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.movie_banner.view.*

class MovieAdapter(
    private val movies: List<Movie>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindMovie(movie: Movie, listener: OnItemClickListener){
            Glide.with(itemView).load(APIConstants.imageBaseUrl + movie.poster).into(itemView.movie_poster)
            itemView.setOnClickListener{ listener.onItemClick(movie) }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_banner,parent,false)
        )
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position),listener)
    }
    override fun getItemCount(): Int = movies.size
}

