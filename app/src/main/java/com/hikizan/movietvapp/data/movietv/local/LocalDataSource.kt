package com.hikizan.movietvapp.data.movietv.local

import com.hikizan.movietvapp.data.movietv.local.model.entity.MovieItemEntity
import com.hikizan.movietvapp.data.movietv.local.model.entity.TvShowItemEntity
import com.hikizan.movietvapp.data.movietv.local.room.MovieTvDao
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
}