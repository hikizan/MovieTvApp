package com.hikizan.movietvapp.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hikizan.movietvapp.core.base.HikizanFragment
import com.hikizan.movietvapp.databinding.FragmentFavoriteTvShowBinding
import com.hikizan.movietvapp.presentation.tv.DetailTvShowsActivity
import com.hikizan.movietvapp.presentation.tv.adapter.TvShowAdapter
import com.hikizan.movietvapp.utils.ext.showDefaultState
import com.hikizan.movietvapp.utils.ext.showEmptyState
import com.hikizan.movietvapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.msvFavoriteTv
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.rvFavoriteTv
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : HikizanFragment() {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    private val favoriteTvShowAdapter = TvShowAdapter()

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
            rvFavoriteTv.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTvShowAdapter
            }
        }
    }

    override fun initAction() {
        favoriteTvShowAdapter.onItemClick = { selectData ->
            DetailTvShowsActivity.start(requireContext(), selectData)
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
        binding.apply {
            favoriteViewModel.getFavoriteTvShows.observe(viewLifecycleOwner) { favoriteTvShows ->
                if (favoriteTvShows.isNullOrEmpty()) {
                    msvFavoriteTv.showEmptyState()
                } else {
                    msvFavoriteTv.showDefaultState()
                    favoriteTvShowAdapter.setData(favoriteTvShows)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}