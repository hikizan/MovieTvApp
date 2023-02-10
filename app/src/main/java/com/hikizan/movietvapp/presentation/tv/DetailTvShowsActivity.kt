package com.hikizan.movietvapp.presentation.tv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.hikizan.movietvapp.base.HikizanActivity
import com.hikizan.movietvapp.databinding.ActivityDetailTvShowsBinding
import com.hikizan.movietvapp.domain.movietv.model.response.TvShowItem
import com.hikizan.movietvapp.utils.constants.AppConstants
import com.hikizan.movietvapp.utils.constants.BundleKeys

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

    private var tvShowItem: TvShowItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailTvShowsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initIntent()
        initUI()
    }

    override fun initIntent() {
        tvShowItem = intent.getParcelableExtra(BundleKeys.TV_SHOW_EXTRA_DATA)
    }

    override fun initUI() {
        binding?.apply {
            tvDetailTitle.text = tvShowItem?.originalName
            tvDetailReleaseDate.text = tvShowItem?.firstAirDate
            tvDetailPopularity.text = tvShowItem?.popularity.toString()
            tvDetailScore.text = tvShowItem?.voteAverage.toString()
            tvDetailOverview.text = tvShowItem?.overview
            Glide.with(this@DetailTvShowsActivity)
                .load(AppConstants.POSTER_PATH + tvShowItem?.posterPath)
                .into(imgDetailPoster)
        }
    }

    override fun initProcess() {
    }

    override fun initObservers() {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}