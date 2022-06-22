package com.sina.navigationkotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sina.navigationkotlin.models.Movie
import com.sina.navigationkotlin.models.MovieResponse
import com.sina.navigationkotlin.services.MovieApiInterface
import com.sina.navigationkotlin.services.MovieApiService
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(), MovieAdapter.OnItemClickListener {

    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)

        view.rv_popular_movies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL,false)
        view.rv_popular_movies.setHasFixedSize(true)

        view.rv_upcoming_movies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL,false)
        view.rv_upcoming_movies.setHasFixedSize(true)

        view.rv_topRated_movies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL,false)
        view.rv_topRated_movies.setHasFixedSize(true)

        view.rv_now_playing.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL,false)
        view.rv_now_playing.setHasFixedSize(true)

        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FRAGMENT_FIRST", "onCreate")
    }
    override fun onResume() {
        super.onResume()
        getBothMovieData()
        Log.d("FRAGMENT_FIRST", "onResume")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("FRAGMENT_FIRST", "onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("FRAGMENT_FIRST", "onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FRAGMENT_FIRST", "onDestroy")
    }

    fun getBothMovieData() {
        getPopularMovieDatas { movies: List<Movie> ->
            rv_popular_movies.adapter = MovieAdapter(movies, this)
        }

        getUpcomingMoviesDatas { movies: List<Movie> ->
            rv_upcoming_movies.adapter = MovieAdapter(movies, this)
        }

        getTopRatedMovies { movies: List<Movie> ->
            rv_topRated_movies.adapter = MovieAdapter(movies, this)
        }

        getNowPlayingMoviesData { movies: List<Movie> ->
            rv_now_playing.adapter = MovieAdapter(movies, this)
        }
    }

    private fun getPopularMovieDatas(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
        })
    }

    private fun getUpcomingMoviesDatas(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getUpcomingMovieList().enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }
        })
    }

    private fun getTopRatedMovies(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getTopRatedMovieList().enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }
        })
    }

    private fun getNowPlayingMoviesData(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getNowPlayingMovieList().enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }
        })
    }
}