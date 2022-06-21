package com.sina.navigationkotlin

import android.content.ContentValues.TAG
import android.content.Context
import android.content.LocusId
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sina.navigationkotlin.models.Movie
import com.sina.navigationkotlin.models.MovieResponse
import com.sina.navigationkotlin.services.MovieApiInterface
import com.sina.navigationkotlin.services.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*
import kotlinx.android.synthetic.main.movie_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondFragment : Fragment(), MovieAdapter.OnItemClickListener {

    override fun onItemClick(movie: Movie) {
        Log.d("MOVIE", movie.title ?: "-")
        (activity as? MainActivity)?.addMovieDetail(movie)
    }

    var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("FRAGMENT_SECOND", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        var view = inflater.inflate(R.layout.fragment_second, container, false)

        view.recycleViewSimilarMovies.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL,false)
        view.recycleViewSimilarMovies.setHasFixedSize(true)

        return view
    }

    override fun onResume() {
        super.onResume()

        movie.let {
            fetchSimilarMovies(movieId = it!!.id!!, callback = { movies: List<Movie> ->
                recycleViewSimilarMovies.adapter = MovieAdapter(movies, this)
            })
        }

        Log.d("FRAGMENT_SECOND", "onResume")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.d("FRAGMENT_SECOND", "onAttach")
    }

    override fun onDetach() {
        super.onDetach()

        Log.d("FRAGMENT_SECOND", "onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("FRAGMENT_SECOND", "onDestroy")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.movie = requireArguments().get("movie") as? Movie

        this.movie.let {
            view.textView4.setText(it!!.title)
            view.textView5.setText(it!!.over )
            view.relaseDateText.setText("Release Date: " + it!!.release)
            view.voteAvarageText.setText("Vote Average: "+ it!!.average.toString())

            Glide.with(view).load("https://image.tmdb.org/t/p/w500/" + it!!.poster).into(view.imageView)
        }
    }


    private fun fetchSimilarMovies(movieId: String, callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieById(movieId).enqueue(object : Callback<MovieResponse>{

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }


}


