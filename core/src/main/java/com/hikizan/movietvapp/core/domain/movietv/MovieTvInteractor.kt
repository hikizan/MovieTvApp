package com.hikizan.movietvapp.core.domain.movietv

import com.hikizan.movietvapp.core.data.movietv.Resource
import com.hikizan.movietvapp.core.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.core.domain.movietv.model.response.TvShowItem
import com.hikizan.movietvapp.core.domain.movietv.repositoryimpl.MovieTvRepositoryImpl
import kotlinx.coroutines.flow.Flow

class MovieTvInteractor(private val repository: MovieTvRepositoryImpl) : MovieTvUseCase {

    override fun getMovies(): Flow<Resource<List<MovieItem>>> {
        return repository.getMovies()
    }

    override fun getTvShows(): Flow<Resource<List<TvShowItem>>> {
        return repository.getTvShows()
    }

    override fun getFavoriteMovies(): Flow<List<MovieItem>> {
        return repository.getFavoriteMovies()
    }

    override fun getFavoriteTvShows(): Flow<List<TvShowItem>> {
        return repository.getFavoriteTvShows()
    }

    override fun setFavoriteMovie(movieItem: MovieItem, state: Boolean) {
        return repository.setFavoriteMovie(movieItem, state)
    }

    override fun setFavoriteTvShow(tvShowItem: TvShowItem, state: Boolean) {
        return repository.setFavoriteTvShow(tvShowItem, state)
    }
}