package com.hikizan.movietvapp.presentation.movie

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION
import android.os.Bundle
import com.bumptech.glide.Glide
import com.hikizan.movietvapp.base.HikizanActivity
import com.hikizan.movietvapp.databinding.ActivityDetailMovieBinding
import com.hikizan.movietvapp.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.utils.constants.AppConstants
import com.hikizan.movietvapp.utils.constants.BundleKeys

class DetailMovieActivity : HikizanActivity() {

    companion object {
        fun start(context: Context, movieItem: MovieItem) {
            context.startActivity(
                Intent(context, DetailMovieActivity::class.java).apply {
                    putExtra(BundleKeys.MOVIE_EXTRA_DATA, movieItem)
                }
            )
        }
    }

    private var _binding: ActivityDetailMovieBinding? = null
    private val binding get() = _binding

    private var movieItem: MovieItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initIntent()
        initUI()
    }

    override fun initIntent() {
        movieItem = if (VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(BundleKeys.MOVIE_EXTRA_DATA, MovieItem::class.java)
        } else {
            intent.getParcelableExtra(BundleKeys.MOVIE_EXTRA_DATA)
        }
    }

    override fun initUI() {
        binding?.apply {
            tvDetailTitle.text = movieItem?.originalTitle
            tvDetailReleaseDate.text = movieItem?.releaseDate
            tvDetailPopularity.text = movieItem?.popularity.toString()
            tvDetailScore.text = movieItem?.voteAverage.toString()
            tvDetailOverview.text = movieItem?.overview
            Glide.with(this@DetailMovieActivity)
                .load(AppConstants.POSTER_PATH + movieItem?.posterPath)
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