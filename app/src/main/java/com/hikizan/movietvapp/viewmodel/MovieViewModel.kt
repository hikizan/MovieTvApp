package com.hikizan.movietvapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hikizan.movietvapp.core.data.movietv.Resource
import com.hikizan.movietvapp.core.domain.movietv.MovieTvUseCase
import com.hikizan.movietvapp.core.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.utils.ext.proceed
import kotlinx.coroutines.launch

class MovieViewModel(private val movieTvUseCase: MovieTvUseCase) : ViewModel() {

    private val _moviesResult: MutableLiveData<Resource<List<MovieItem>>> = MutableLiveData()

    val moviesResult: LiveData<Resource<List<MovieItem>>> get() = _moviesResult

    fun getMovies() = viewModelScope.launch {
        _moviesResult.value = Resource.Loading()
        proceed(_moviesResult) {
            movieTvUseCase.getMovies()
        }
    }

    fun setFavoriteMovie(movieItem: MovieItem, isFavorite: Boolean) =
        movieTvUseCase.setFavoriteMovie(movieItem, isFavorite)
}