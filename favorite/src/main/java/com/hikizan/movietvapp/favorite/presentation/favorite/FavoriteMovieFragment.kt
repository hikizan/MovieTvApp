package com.hikizan.movietvapp.favorite.presentation.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hikizan.movietvapp.core.base.HikizanFragment
import com.hikizan.movietvapp.core.presentation.movie.adapter.MovieAdapter
import com.hikizan.movietvapp.core.utils.constants.BundleKeys
import com.hikizan.movietvapp.core.utils.ext.showDefaultState
import com.hikizan.movietvapp.core.utils.ext.showEmptyState
import com.hikizan.movietvapp.core.utils.ext.showToast
import com.hikizan.movietvapp.favorite.databinding.FragmentFavoriteMovieBinding
import com.hikizan.movietvapp.favorite.di.featuremodule.favoriteModule
import com.hikizan.movietvapp.favorite.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

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
        binding?.apply {
            rvFavoriteMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteMovieAdapter
            }
        }
    }

    override fun initAction() {
        favoriteMovieAdapter.onItemClick = { selectData ->
            try {
                val moveToDetailPage = Intent(
                    requireContext(),
                    Class.forName("com.hikizan.movietvapp.presentation.movie.DetailMovieActivity")
                )
                moveToDetailPage.putExtra(BundleKeys.MOVIE_EXTRA_DATA, selectData)
                startActivity(moveToDetailPage)
            } catch (e: Exception) {
                context?.showToast(e.toString())
            }
        }
    }

    override fun initProcess() {
        loadKoinModules(favoriteModule)
    }

    override fun initObservers() {
        binding?.apply {
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