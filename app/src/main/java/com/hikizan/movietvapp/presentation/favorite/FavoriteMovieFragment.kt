package com.hikizan.movietvapp.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hikizan.movietvapp.core.base.HikizanFragment
import com.hikizan.movietvapp.databinding.FragmentFavoriteMovieBinding
import com.hikizan.movietvapp.presentation.movie.DetailMovieActivity
import com.hikizan.movietvapp.presentation.movie.adapter.MovieAdapter
import com.hikizan.movietvapp.utils.ext.showDefaultState
import com.hikizan.movietvapp.utils.ext.showEmptyState
import com.hikizan.movietvapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite_movie.msvFavoriteMovie
import kotlinx.android.synthetic.main.fragment_favorite_movie.rvFavoriteMovie
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : HikizanFragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    private val favoriteMovieAdapter = MovieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            initUI()
            initAction()
            initProcess()
        }
    }

    override fun initIntent() {
    }

    override fun initUI() {
        binding.apply {
            rvFavoriteMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteMovieAdapter
            }
        }
    }

    override fun initAction() {
        favoriteMovieAdapter.onItemClick = { selectData ->
            DetailMovieActivity.start(requireContext(), selectData)
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
        binding.apply {
            favoriteViewModel.getFavoriteMovies.observe(viewLifecycleOwner) { favoriteMovies ->
                if (favoriteMovies.isNullOrEmpty()) {
                    msvFavoriteMovie.showEmptyState()
                } else {
                    msvFavoriteMovie.showDefaultState()
                    favoriteMovieAdapter.setData(favoriteMovies)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}