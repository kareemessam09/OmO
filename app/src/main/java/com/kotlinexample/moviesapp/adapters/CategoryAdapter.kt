package com.kotlinexample.moviesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.models.CategoryData

class CategoryAdapter ( var categoryList : List<CategoryData>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemIcon : ImageView = itemView.findViewById(R.id.itemIcon)
        val itemTitle : TextView = itemView.findViewById(R.id.itemTitle)
    }

    fun setFilteredList(categoryList: List<CategoryData>){
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item , parent , false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.itemIcon.setImageResource(categoryList[position].categoryIcon)
        holder.itemTitle.text = categoryList[position].categoryTitle
    }

}