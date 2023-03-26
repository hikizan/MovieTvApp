package com.hikizan.movietvapp.presentation.movie

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.hikizan.movietvapp.R
import com.hikizan.movietvapp.base.BaseActivity
import com.hikizan.movietvapp.core.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.core.utils.constants.AppConstants
import com.hikizan.movietvapp.core.utils.constants.BundleKeys
import com.hikizan.movietvapp.core.utils.ext.orEmptyString
import com.hikizan.movietvapp.core.utils.ext.setupHikizanToolbar
import com.hikizan.movietvapp.core.utils.ext.showToast
import com.hikizan.movietvapp.databinding.ActivityDetailMovieBinding
import com.hikizan.movietvapp.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>() {

    companion object {
        fun start(context: Context, movieItem: MovieItem) {
            context.startActivity(
                Intent(context, DetailMovieActivity::class.java).apply {
                    putExtra(BundleKeys.MOVIE_EXTRA_DATA, movieItem)
                }
            )
        }
    }

    /*private var _binding: ActivityDetailMovieBinding? = null
    private val binding get() = _binding*/

    private val movieViewModel: MovieViewModel by viewModel()

    private var movieItem: MovieItem? = null
    private var isFavorite: Boolean = false

    override fun initViewBinding(): ActivityDetailMovieBinding {
        return ActivityDetailMovieBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*_binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding?.root)*/

        initIntent()
        initUI()
        initAction()
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
            setupHikizanToolbar(
                toolbarLayout = layoutToolbarMovie,
                title = movieItem?.title.orEmptyString(),
                isChild = true,
                isFavoriteVisible = true
            )

            tvDetailTitle.text = movieItem?.originalTitle
            tvDetailReleaseDate.text = movieItem?.releaseDate
            tvDetailPopularity.text = movieItem?.popularity.toString()
            tvDetailScore.text = movieItem?.voteAverage.toString()
            tvDetailOverview.text = movieItem?.overview
            Glide.with(this@DetailMovieActivity)
                .load(AppConstants.POSTER_PATH + movieItem?.posterPath)
                .into(imgDetailPoster)
            movieItem?.isFavorite?.let {
                setFavoriteStatus(it)
                isFavorite = it
            }
        }
    }

    override fun initAction() {
        binding?.apply {
            layoutToolbarMovie.imgFavorite.setOnClickListener {
                isFavorite = !isFavorite
                movieItem?.let { dataMovie ->
                    movieViewModel.setFavoriteMovie(dataMovie, isFavorite)
                    this@DetailMovieActivity.showToast(
                        if (isFavorite) {
                            getString(R.string.message_add_favorite, dataMovie.title)
                        } else {
                            getString(R.string.message_remove_favorite, dataMovie.title)
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


    private fun setFavoriteStatus(isFavorite: Boolean) = binding?.apply {
        if (isFavorite) {
            layoutToolbarMovie.imgFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailMovieActivity,
                    R.drawable.ic_favorite
                )
            )
        } else {
            layoutToolbarMovie.imgFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailMovieActivity,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }
}