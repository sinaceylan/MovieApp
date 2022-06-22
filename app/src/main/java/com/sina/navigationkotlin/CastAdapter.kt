package com.sina.navigationkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sina.navigationkotlin.models.MovieCast
import kotlinx.android.synthetic.main.profile_card.view.*

class CastAdapter (
    private val movieCasts: List<MovieCast>,
    ): RecyclerView.Adapter<CastAdapter.CastViewHolder>() {
    class CastViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val PROFILE_IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bindCast(casts: MovieCast) {
            itemView.actorName.text = casts.name
            itemView.characterName.text = casts.character

            Glide.with(itemView).load(PROFILE_IMAGE_BASE + casts.profile).into(itemView.profilePicture)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.profile_card, parent, false)
        )
    }
    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bindCast(movieCasts.get(position))
    }
    override fun getItemCount(): Int = movieCasts.size
    }


