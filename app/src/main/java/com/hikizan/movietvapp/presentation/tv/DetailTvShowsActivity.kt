package com.hikizan.movietvapp.presentation.tv

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.hikizan.movietvapp.R
import com.hikizan.movietvapp.core.base.HikizanActivity
import com.hikizan.movietvapp.databinding.ActivityDetailTvShowsBinding
import com.hikizan.movietvapp.core.domain.movietv.model.response.TvShowItem
import com.hikizan.movietvapp.core.utils.constants.AppConstants
import com.hikizan.movietvapp.core.utils.constants.BundleKeys
import com.hikizan.movietvapp.core.utils.ext.orEmptyString
import com.hikizan.movietvapp.core.utils.ext.setupHikizanToolbar
import com.hikizan.movietvapp.utils.ext.showToast
import com.hikizan.movietvapp.viewmodel.TvShowViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTvShowsActivity : HikizanActivity() {

    companion object {
        fun start(context: Context, tvShowItem: TvShowItem) {
            context.startActivity(
                Intent(context, DetailTvShowsActivity::class.java).apply {
                    putExtra(BundleKeys.TV_SHOW_EXTRA_DATA, tvShowItem)
                }
            )
        }
    }

    private var _binding: ActivityDetailTvShowsBinding? = null
    private val binding get() = _binding

    private val tvShowViewModel: TvShowViewModel by viewModel()

    private var tvShowItem: TvShowItem? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailTvShowsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initIntent()
        initUI()
        initAction()
    }

    override fun initIntent() {
        tvShowItem = if (VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(BundleKeys.TV_SHOW_EXTRA_DATA, TvShowItem::class.java)
        } else {
            intent.getParcelableExtra(BundleKeys.TV_SHOW_EXTRA_DATA)
        }
    }

    override fun initUI() {
        binding?.apply {
            setupHikizanToolbar(
                toolbarLayout = layoutToolbarTvShow,
                title = tvShowItem?.name.orEmptyString(),
                isChild = true,
                isFavoriteVisible = true
            )

            tvDetailTitle.text = tvShowItem?.originalName
            tvDetailReleaseDate.text = tvShowItem?.firstAirDate
            tvDetailPopularity.text = tvShowItem?.popularity.toString()
            tvDetailScore.text = tvShowItem?.voteAverage.toString()
            tvDetailOverview.text = tvShowItem?.overview
            Glide.with(this@DetailTvShowsActivity)
                .load(AppConstants.POSTER_PATH + tvShowItem?.posterPath)
                .into(imgDetailPoster)
            tvShowItem?.isFavorite?.let {
                setFavoriteStatus(it)
                isFavorite = it
            }
        }
    }

    override fun initAction() {
        binding?.apply {
            layoutToolbarTvShow.imgFavorite.setOnClickListener {
                isFavorite = !isFavorite
                tvShowItem?.let { dataTvShow ->
                    tvShowViewModel.setFavoriteTvShow(dataTvShow, isFavorite)
                    this@DetailTvShowsActivity.showToast(
                        if (isFavorite) {
                            getString(R.string.message_add_favorite, dataTvShow.name)
                        } else {
                            getString(R.string.message_remove_favorite, dataTvShow.name)
                        }
                    )
                }
                setFavoriteStatus(isFavorite)
            }
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setFavoriteStatus(isFavorite: Boolean) = binding?.apply {
        layoutToolbarTvShow.imgFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this@DetailTvShowsActivity,
                if (isFavorite) R.drawable.ic_favorite
                else R.drawable.ic_favorite_border
            )
        )
    }
}