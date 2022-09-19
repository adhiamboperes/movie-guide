package com.adhiambo.movieguide.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.adhiambo.movieguide.R
import com.adhiambo.movieguide.databinding.ContentMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ContentMainBinding
    private lateinit var viewModel: MoviesViewModel

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]

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