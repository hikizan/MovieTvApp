package com.hikizan.movietvapp.presentation.movie

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hikizan.movietvapp.R
import com.hikizan.movietvapp.base.BaseFragment
import com.hikizan.movietvapp.core.data.movietv.Resource
import com.hikizan.movietvapp.core.presentation.movie.adapter.MovieAdapter
import com.hikizan.movietvapp.databinding.FragmentMovieBinding
import com.hikizan.movietvapp.utils.ext.showDefaultState
import com.hikizan.movietvapp.utils.ext.showErrorState
import com.hikizan.movietvapp.utils.ext.showLoadingState
import com.hikizan.movietvapp.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment<FragmentMovieBinding>() {

    /*private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding*/

    private val movieViewModel: MovieViewModel by viewModel()

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }*/

    private val movieAdapter = MovieAdapter()

    override fun initViewBinding(): FragmentMovieBinding {
        return FragmentMovieBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            initUI()
            initProcess()
            initObservers()
        }
    }

    override fun initIntent() {
    }

    override fun initUI() {

        binding?.rvMovie?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        movieAdapter.onItemClick = { selectData ->
            DetailMovieActivity.start(requireContext(), selectData)
        }
    }

    override fun initAction() {
    }

    override fun initProcess() {
        movieViewModel.getMovies()
    }

    override fun initObservers() {
        binding?.apply {
            movieViewModel.moviesResult.observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            msvMovie.showLoadingState()
                        }
                        is Resource.Success -> {
                            msvMovie.showDefaultState()
                            movieAdapter.setData(movies.data)
                        }
                        is Resource.Error -> {
                            msvMovie.showErrorState(
                                message = movies.message ?: getString(R.string.message_error_state),
                                action = Pair(getString(R.string.action_retry)) {
                                    movieViewModel.getMovies()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}