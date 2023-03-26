package com.hikizan.movietvapp.presentation.tv

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hikizan.movietvapp.R
import com.hikizan.movietvapp.base.BaseFragment
import com.hikizan.movietvapp.core.data.movietv.Resource
import com.hikizan.movietvapp.core.presentation.tv.adapter.TvShowAdapter
import com.hikizan.movietvapp.databinding.FragmentTvshowBinding
import com.hikizan.movietvapp.utils.ext.showDefaultState
import com.hikizan.movietvapp.utils.ext.showErrorState
import com.hikizan.movietvapp.utils.ext.showLoadingState
import com.hikizan.movietvapp.viewmodel.TvShowViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvshowFragment : BaseFragment<FragmentTvshowBinding>() {

    /*private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding*/

    private val tvShowViewModel: TvShowViewModel by viewModel()
    private val tvShowAdapter = TvShowAdapter()

    override fun initViewBinding(): FragmentTvshowBinding {
        return FragmentTvshowBinding.inflate(layoutInflater)
    }

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

}