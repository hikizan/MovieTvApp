package com.hikizan.movietvapp.domain.movietv.repositoryimpl

import com.hikizan.movietvapp.data.movietv.Resource
import com.hikizan.movietvapp.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.domain.movietv.model.response.TvShowItem
import kotlinx.coroutines.flow.Flow

interface MovieTvRepositoryImpl {
    fun getMovies(): Flow<Resource<List<MovieItem>>>

    fun getTvShows(): Flow<Resource<List<TvShowItem>>>

    fun getFavoriteMovies(): Flow<List<MovieItem>>

    fun getFavoriteTvShows(): Flow<List<TvShowItem>>

    fun setFavoriteMovie(movieItem: MovieItem, state: Boolean)

    fun setFavoriteTvShow(tvShowItem: TvShowItem, state: Boolean)
}