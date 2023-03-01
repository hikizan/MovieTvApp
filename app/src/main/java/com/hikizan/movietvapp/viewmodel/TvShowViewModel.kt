package com.hikizan.movietvapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hikizan.movietvapp.core.data.movietv.Resource
import com.hikizan.movietvapp.core.domain.movietv.MovieTvUseCase
import com.hikizan.movietvapp.core.domain.movietv.model.response.TvShowItem
import com.hikizan.movietvapp.utils.ext.proceed
import kotlinx.coroutines.launch

class TvShowViewModel(private val movieTvUseCase: MovieTvUseCase) : ViewModel() {

    private val _tvShowsResult: MutableLiveData<Resource<List<TvShowItem>>> = MutableLiveData()

    val tvShowsResult: LiveData<Resource<List<TvShowItem>>> get() = _tvShowsResult

    fun getTvShows() = viewModelScope.launch {
        _tvShowsResult.value = Resource.Loading()
        proceed(_tvShowsResult) {
            movieTvUseCase.getTvShows()
        }
    }

    fun setFavoriteTvShow(tvShowItem: TvShowItem, isFavorite: Boolean) =
        movieTvUseCase.setFavoriteTvShow(tvShowItem, isFavorite)
}