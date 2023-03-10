package com.hikizan.movietvapp.presentation.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hikizan.movietvapp.R
import com.hikizan.movietvapp.core.base.HikizanFragment
import com.hikizan.movietvapp.core.data.movietv.Resource
import com.hikizan.movietvapp.core.presentation.tv.adapter.TvShowAdapter
import com.hikizan.movietvapp.databinding.FragmentTvshowBinding
import com.hikizan.movietvapp.utils.ext.showDefaultState
import com.hikizan.movietvapp.utils.ext.showErrorState
import com.hikizan.movietvapp.utils.ext.showLoadingState
import com.hikizan.movietvapp.viewmodel.TvShowViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvshowFragment : HikizanFragment() {

    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    private val tvShowViewModel: TvShowViewModel by viewModel()
    private val tvShowAdapter = TvShowAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initProcess()
        initObservers()
    }

    override fun initIntent() {
    }

    override fun initUI() {

        binding?.rvTv?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        tvShowAdapter.onItemClick = { selectData ->
            DetailTvShowsActivity.start(requireContext(), selectData)
        }
    }

    override fun initAction() {
    }

    override fun initProcess() {
        tvShowViewModel.getTvShows()
    }

    override fun initObservers() {
        binding?.apply {
            tvShowViewModel.tvShowsResult.observe(viewLifecycleOwner) { tvShows ->
                if (tvShows != null) {
                    when (tvShows) {
                        is Resource.Loading -> {
                            msvTv.showLoadingState()
                        }
                        is Resource.Success -> {
                            msvTv.showDefaultState()
                            tvShowAdapter.setData(tvShows.data)
                        }
                        is Resource.Error -> {
                            msvTv.showErrorState(
                                message = tvShows.message ?: getString(R.string.message_error_state),
                                action = Pair(getString(R.string.action_retry)) {
                                    tvShowViewModel.getTvShows()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}