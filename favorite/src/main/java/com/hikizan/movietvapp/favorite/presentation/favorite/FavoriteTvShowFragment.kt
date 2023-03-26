package com.hikizan.movietvapp.favorite.presentation.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hikizan.movietvapp.core.presentation.tv.adapter.TvShowAdapter
import com.hikizan.movietvapp.core.utils.constants.BundleKeys
import com.hikizan.movietvapp.core.utils.ext.showDefaultState
import com.hikizan.movietvapp.core.utils.ext.showEmptyState
import com.hikizan.movietvapp.core.utils.ext.showToast
import com.hikizan.movietvapp.favorite.R
import com.hikizan.movietvapp.favorite.base.BaseFragment
import com.hikizan.movietvapp.favorite.databinding.FragmentFavoriteTvShowBinding
import com.hikizan.movietvapp.favorite.di.featuremodule.favoriteModule
import com.hikizan.movietvapp.favorite.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteTvShowFragment : BaseFragment<FragmentFavoriteTvShowBinding>() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun initViewBinding(): FragmentFavoriteTvShowBinding {
        return FragmentFavoriteTvShowBinding.inflate(layoutInflater)
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
        binding?.apply {
            rvFavoriteTv.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTvShowAdapter
            }
        }
    }

    override fun initAction() {
        favoriteTvShowAdapter.onItemClick = { selectData ->
            try {
                val moveToDetailPage = Intent(
                    requireContext(),
                    Class.forName(getString(R.string.module_app_detail_tv))
                )
                moveToDetailPage.putExtra(BundleKeys.TV_SHOW_EXTRA_DATA, selectData)
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
}