package com.sina.navigationkotlin.services

import com.sina.navigationkotlin.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface{
    @GET("/3/movie/popular?api_key=a3f7af2f8e55cf0e73fe3cd8d2b0c423")
    fun getMovieList(): Call<MovieResponse>

    @GET("/3/movie/upcoming?api_key=a3f7af2f8e55cf0e73fe3cd8d2b0c423")
    fun getUpcomingMovieList(): Call<MovieResponse>

    @GET("/3/movie/top_rated?api_key=a3f7af2f8e55cf0e73fe3cd8d2b0c423")
    fun getTopRatedMovieList(): Call<MovieResponse>

    @GET("/3/movie/now_playing?api_key=a3f7af2f8e55cf0e73fe3cd8d2b0c423")
    fun getNowPlayingMovieList(): Call<MovieResponse>



}