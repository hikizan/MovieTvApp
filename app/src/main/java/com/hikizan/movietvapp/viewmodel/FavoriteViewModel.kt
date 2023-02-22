package com.hikizan.movietvapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hikizan.movietvapp.domain.movietv.MovieTvUseCase

class FavoriteViewModel(movieTvUseCase: MovieTvUseCase) : ViewModel() {

    val getFavoriteMovies = movieTvUseCase.getFavoriteMovies().asLiveData()

    val getFavoriteTvShows = movieTvUseCase.getFavoriteTvShows().asLiveData()
}