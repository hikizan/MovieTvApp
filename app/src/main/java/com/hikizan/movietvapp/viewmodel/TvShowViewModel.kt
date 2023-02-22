package com.hikizan.movietvapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hikizan.movietvapp.domain.movietv.MovieTvUseCase
import com.hikizan.movietvapp.domain.movietv.model.response.TvShowItem

class TvShowViewModel(private val movieTvUseCase: MovieTvUseCase) : ViewModel() {
    val tvShows = movieTvUseCase.getTvShows().asLiveData()

    fun setFavoriteTvShow(tvShowItem: TvShowItem, isFavorite: Boolean) =
        movieTvUseCase.setFavoriteTvShow(tvShowItem, isFavorite)
}