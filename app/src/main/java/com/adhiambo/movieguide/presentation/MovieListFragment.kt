package com.adhiambo.movieguide.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhiambo.movieguide.R
import com.adhiambo.movieguide.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MoviesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_list, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
        initializeAdapter()
    }

    private fun initializeObservers() {
        viewModel.getMovies()
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.addItems(movies)
        }

        viewModel.fetchStateLiveData.observe(viewLifecycleOwner) { status ->
            if (status == MoviesViewModel.FetchState.FETCH_FAILED) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_could_not_fetch_movies),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun initializeAdapter() {
        moviesAdapter = MoviesAdapter()
        moviesAdapter.setHasStableIds(true)

        binding.movieList.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}