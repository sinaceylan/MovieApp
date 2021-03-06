package com.sina.navigationkotlin

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.sina.navigationkotlin.models.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showMainList()
        }
    }

    fun showMainList() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<MainFragment>(R.id.fragmentContainerView)
            addToBackStack("movie_navigation_stack")
        }
    }

    fun addMovieDetail(movie: Movie) {
        val bundle = bundleOf("movie" to movie)
        supportFragmentManager.commit {
            replace<MovieDetailFragment>(R.id.fragmentContainerView, args = bundle)
            setReorderingAllowed(true)
            addToBackStack("movie_navigation_stack")
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 1) {

            val eBuilder = AlertDialog.Builder(this)
            eBuilder.setTitle("Exit")
            eBuilder.setIcon(R.drawable.ic_action_name)
            eBuilder.setMessage("Do you want to exit ?")
            eBuilder.setPositiveButton("Yes"){
                Dialog,which->
                finish()
            }
            eBuilder.setNegativeButton("Cancel"){
                Dialog,which->
            }
            val createBuild= eBuilder.create()
            createBuild.show()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}