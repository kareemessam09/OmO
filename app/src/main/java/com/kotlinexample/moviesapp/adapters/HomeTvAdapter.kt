package com.kotlinexample.moviesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinexample.moviesapp.databinding.MovieHomeItemBinding
import com.kotlinexample.moviesapp.databinding.TvHomeItemBinding
import com.kotlinexample.moviesapp.models.Tv

class HomeTvAdapter(private val tvs:List<Tv> , val context:Context) : RecyclerView.Adapter<TvHomeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvHomeViewHolder {
        val x = TvHomeItemBinding.inflate(LayoutInflater.from(context),parent,false,)

        return TvHomeViewHolder(x)
    }

    override fun getItemCount(): Int {
        return tvs.size
    }

    override fun onBindViewHolder(holder: TvHomeViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w342${tvs[position].posterPath}")
            .into(holder.poster)

    }

}

class TvHomeViewHolder(itemView: TvHomeItemBinding): RecyclerView.ViewHolder(itemView.root){
    val poster = itemView.posterImage

}