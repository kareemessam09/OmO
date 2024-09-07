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

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = "c0159f039b157d30f17a9c31ed17794f",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("tv/top_rated")
    fun getTopRatedTvShows(
        @Query("api_key") apiKey : String = "c0159f039b157d30f17a9c31ed17794f",
        @Query("page") page: Int
    ): Call<GetTvResponse>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey : String = "c0159f039b157d30f17a9c31ed17794f",
        @Query("page") page: Int
    ): Call<GetTvResponse>

    @GET("tv/on_the_air")
    fun getOnTheAirTvShows(
        @Query("api_key") apiKey : String = "c0159f039b157d30f17a9c31ed17794f",
        @Query("page") page: Int
    ): Call<GetTvResponse>

    @GET("tv/airing_today")
    fun getAiringTodayTvShows(
        @Query("api_key") apiKey : String = "c0159f039b157d30f17a9c31ed17794f",
        @Query("page") page: Int
    ): Call<GetTvResponse>




}