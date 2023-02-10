package com.hikizan.movietvapp.domain.movietv

import com.hikizan.movietvapp.data.movietv.Resource
import com.hikizan.movietvapp.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.domain.movietv.model.response.TvShowItem
import kotlinx.coroutines.flow.Flow

interface MovieTvUseCase {
    fun getMovies(): Flow<Resource<List<MovieItem>>>
    fun getTvShows(): Flow<Resource<List<TvShowItem>>>
}