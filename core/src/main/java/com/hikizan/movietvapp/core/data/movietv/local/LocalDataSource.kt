package com.hikizan.movietvapp.core.data.movietv.local

import com.hikizan.movietvapp.core.data.movietv.local.model.entity.MovieItemEntity
import com.hikizan.movietvapp.core.data.movietv.local.model.entity.TvShowItemEntity
import com.hikizan.movietvapp.core.data.movietv.local.room.MovieTvDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieTvDao: MovieTvDao) {

    fun getMovies(): Flow<List<MovieItemEntity>> =
        movieTvDao.getMovies()

    fun getTvShows(): Flow<List<TvShowItemEntity>> =
        movieTvDao.getTvShows()

    suspend fun insertMovies(movies: List<MovieItemEntity>) =
        movieTvDao.insertMovies(movies)

    suspend fun insertTvShows(tvShows: List<TvShowItemEntity>) =
        movieTvDao.insertTvShows(tvShows)

    fun getFavoriteMovies(): Flow<List<MovieItemEntity>> =
        movieTvDao.getFavoriteMovies()

    fun getFavoriteTvShows(): Flow<List<TvShowItemEntity>> =
        movieTvDao.getFavoriteTvShows()

    fun setFavoriteMovie(movie: MovieItemEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieTvDao.updateFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowItemEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        movieTvDao.updateFavoriteTvShow(tvShow)
    }
}