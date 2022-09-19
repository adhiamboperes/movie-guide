package com.adhiambo.movieguide.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.adhiambo.movieguide.R
import com.adhiambo.movieguide.databinding.ContentMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ContentMainBinding

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.content_main)
        binding.lifecycleOwner = this

        setUpNavigation()
    }

    private fun setUpNavigation() {
        val bottomNavigationView = binding.bottomNavBar

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_movies -> navController.navigate(R.id.movieListFragment)
            }
            true
        }
    }
}