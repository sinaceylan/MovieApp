package com.sina.navigationkotlin

import android.content.ContentValues.TAG
import android.content.Context
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
import com.bumptech.glide.Glide
import com.sina.navigationkotlin.models.Movie
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*
import kotlinx.android.synthetic.main.movie_item.view.*

class SecondFragment : Fragment() {

    var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Log.d("FRAGMENT_SECOND", "onCreate")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.activityMainLay)
            }
        }
        */


        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onResume() {
        super.onResume()

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

}


