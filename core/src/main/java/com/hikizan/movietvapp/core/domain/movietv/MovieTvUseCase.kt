package com.hikizan.movietvapp.core.domain.movietv

import com.hikizan.movietvapp.core.data.movietv.Resource
import com.hikizan.movietvapp.core.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.core.domain.movietv.model.response.TvShowItem
import kotlinx.coroutines.flow.Flow

interface MovieTvUseCase {
    fun getMovies(): Flow<Resource<List<MovieItem>>>
    fun getTvShows(): Flow<Resource<List<TvShowItem>>>
    fun getFavoriteMovies(): Flow<List<MovieItem>>
    fun getFavoriteTvShows(): Flow<List<TvShowItem>>
    fun setFavoriteMovie(movieItem: MovieItem, state: Boolean)
    fun setFavoriteTvShow(tvShowItem: TvShowItem, state: Boolean)
}