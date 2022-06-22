package com.sina.navigationkotlin.services

import com.sina.navigationkotlin.APIConstants
import com.sina.navigationkotlin.models.MovieCastResponse
import com.sina.navigationkotlin.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_KEY = "api_key=" + APIConstants.apiKey

interface MovieApiInterface{
    @GET("/3/movie/popular?$API_KEY")
    fun getMovieList(): Call<MovieResponse>

    @GET("/3/movie/upcoming?$API_KEY")
    fun getUpcomingMovieList(): Call<MovieResponse>

    @GET("/3/movie/top_rated?$API_KEY")
    fun getTopRatedMovieList(): Call<MovieResponse>

    @GET("/3/movie/now_playing?$API_KEY")
    fun getNowPlayingMovieList(): Call<MovieResponse>

    @GET("/3/movie/{id}/similar?$API_KEY")
    fun getMovieById(
        @Path("id") id:String) :Call<MovieResponse>

    @GET("/3/movie/{id}/credits?$API_KEY")
    fun getCastById(
        @Path("id") id:String) :Call<MovieCastResponse>
}