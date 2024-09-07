package com.kotlinexample.moviesapp.data.repository

import com.kotlinexample.moviesapp.data.remote.Api
import com.kotlinexample.moviesapp.data.remote.GetMoviesResponse
import com.kotlinexample.moviesapp.data.remote.GetTvResponse
import com.kotlinexample.moviesapp.models.Tv
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TvRepository {

    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getTopRatedTvShows(
        page: Int = 1,
        onSuccess: (tvs: List<Tv>) -> Unit,
        onError: () -> Unit
    ) {
        api.getTopRatedTvShows(page = page)
            .enqueue(object : Callback<GetTvResponse> {
                override fun onResponse(
                    call: Call<GetTvResponse>,
                    response: Response<GetTvResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvs)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTvResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getPopularTvShows(
        page: Int = 1,
        onSuccess: (tvs: List<Tv>) -> Unit,
        onError: () -> Unit
    ) {
        api.getPopularTvShows(page = page)
            .enqueue(object : Callback<GetTvResponse> {
                override fun onResponse(
                    call: Call<GetTvResponse>,
                    response: Response<GetTvResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvs)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTvResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getOnTheAirTvShows(
        page: Int = 1,
        onSuccess: (tvs: List<Tv>) -> Unit,
        onError: () -> Unit
    ) {
        api.getOnTheAirTvShows(page = page)
            .enqueue(object : Callback<GetTvResponse> {
                override fun onResponse(
                    call: Call<GetTvResponse>,
                    response: Response<GetTvResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvs)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTvResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getAiringTodayTvShows(
        page: Int = 1,
        onSuccess: (tvs: List<Tv>) -> Unit,
        onError: () -> Unit
    ) {
        api.getAiringTodayTvShows(page = page)
            .enqueue(object : Callback<GetTvResponse> {
                override fun onResponse(
                    call: Call<GetTvResponse>,
                    response: Response<GetTvResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvs)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetTvResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }



}



