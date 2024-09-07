package com.kotlinexample.moviesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinexample.moviesapp.databinding.MovieTrendItemBinding
import com.kotlinexample.moviesapp.models.Tv

class TvAdapter (private val tvs:List<Tv>, val context: Context): RecyclerView.Adapter<TvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val v = MovieTrendItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return TvViewHolder(v)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w342${tvs[position].posterPath}")
            .into(holder.tvPoster)


//        holder.itemView.setOnClickListener {
//            val action = TvFragmentDirections.actionTvFragmentToTvShowFragment(tvs[position])
//            it.findNavController().navigate(action)
//        }

    }

    override fun getItemCount(): Int = tvs.size
}

class TvViewHolder(tvItem: MovieTrendItemBinding) : RecyclerView.ViewHolder(tvItem.root) {
    val tvPoster = tvItem.posterImage

}