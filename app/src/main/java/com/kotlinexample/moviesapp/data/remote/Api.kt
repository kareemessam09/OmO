package com.kotlinexample.moviesapp.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "c0159f039b157d30f17a9c31ed17794f",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "c0159f039b157d30f17a9c31ed17794f",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "c0159f039b157d30f17a9c31ed17794f",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}