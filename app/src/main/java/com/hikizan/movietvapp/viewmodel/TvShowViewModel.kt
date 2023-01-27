package com.hikizan.movietvapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hikizan.movietvapp.domain.movietv.MovieTvUseCase

class TvShowViewModel(movieTvUseCase: MovieTvUseCase) : ViewModel() {
    val tvShows = movieTvUseCase.getTvShows().asLiveData()
}