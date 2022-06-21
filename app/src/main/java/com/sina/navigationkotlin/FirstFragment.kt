package com.sina.navigationkotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sina.navigationkotlin.models.Movie
import com.sina.navigationkotlin.models.MovieResponse
import com.sina.navigationkotlin.services.MovieApiInterface
import com.sina.navigationkotlin.services.MovieApiService
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment : Fragment(), MovieAdapter.OnItemClickListener {

    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_first, container, false)


        view.rv_movies_list.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL,false)
        view.rv_movies_list.setHasFixedSize(true)
        //view.rv_movies_list.set

        view.rv_movies_list2.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL,false)
        view.rv_movies_list2.setHasFixedSize(true)

        view.rv_movies_list3.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL,false)
        view.rv_movies_list3.setHasFixedSize(true)

        view.rv_movies_list4.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL,false)
        view.rv_movies_list4.setHasFixedSize(true)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //getBothMovieData()
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
        getMovieData { movies: List<Movie> ->
            rv_movies_list.adapter = MovieAdapter(movies, this)

        }

        getMovieData2 { movies: List<Movie> ->
            rv_movies_list2.adapter = MovieAdapter(movies, this)

        }

        getMovieData3 { movies: List<Movie> ->
            rv_movies_list3.adapter = MovieAdapter(movies, this)

        }

        getMovieData4 { movies: List<Movie> ->
            rv_movies_list4.adapter = MovieAdapter(movies, this)

        }
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }


    private fun getMovieData2(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getUpcomingMovieList().enqueue(object : Callback<MovieResponse>{

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }

    private fun getMovieData3(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getTopRatedMovieList().enqueue(object : Callback<MovieResponse>{

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }

    private fun getMovieData4(callback: (List<Movie>) -> Unit){
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