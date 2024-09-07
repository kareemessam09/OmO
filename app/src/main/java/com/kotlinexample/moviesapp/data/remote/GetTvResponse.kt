package com.kotlinexample.moviesapp.data.remote

import com.google.gson.annotations.SerializedName
import com.kotlinexample.moviesapp.models.Tv

data class GetTvResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val tvs: List<Tv>,
    @SerializedName("total_pages")  val totalPages: Int
)
