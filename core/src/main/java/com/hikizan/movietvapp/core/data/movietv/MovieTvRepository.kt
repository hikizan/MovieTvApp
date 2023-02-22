package com.hikizan.movietvapp.core.data.movietv

import com.hikizan.movietvapp.core.data.movietv.local.LocalDataSource
import com.hikizan.movietvapp.core.data.movietv.remote.RemoteDataSource
import com.hikizan.movietvapp.core.data.movietv.remote.model.response.MovieItemResponse
import com.hikizan.movietvapp.core.data.movietv.remote.model.response.TvShowItemResponse
import com.hikizan.movietvapp.core.data.movietv.remote.network.ApiResponseResult
import com.hikizan.movietvapp.core.domain.movietv.mapper.*
import com.hikizan.movietvapp.core.domain.movietv.model.response.*
import com.hikizan.movietvapp.core.domain.movietv.repositoryimpl.MovieTvRepositoryImpl
import com.hikizan.movietvapp.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class MovieTvRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieTvRepositoryImpl {

    override fun getMovies(): Flow<Resource<List<MovieItem>>> =
        object : NetworkBoundResource<List<MovieItem>, List<MovieItemResponse>>() {
            override fun loadFromDB(): Flow<List<MovieItem>> {
                return localDataSource.getMovies().map { moviesEntity ->
                    moviesEntity.map { it.mapToDomain() }
                }
            }

            override fun shouldFetch(data: List<MovieItem>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponseResult<List<MovieItemResponse>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieItemResponse>) {
                val movieList = data.map { it.mapToEntities() }
                localDataSource.insertMovies(movieList)
            }

        }.asFlow()

    override fun getTvShows(): Flow<Resource<List<TvShowItem>>> =
        object : NetworkBoundResource<List<TvShowItem>, List<TvShowItemResponse>>() {
            override fun loadFromDB(): Flow<List<TvShowItem>> {
                return localDataSource.getTvShows().map { tvShowsEntity ->
                    tvShowsEntity.map { it.mapToDomain() }
                }
            }

            override fun shouldFetch(data: List<TvShowItem>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponseResult<List<TvShowItemResponse>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveCallResult(data: List<TvShowItemResponse>) {
                val tvShowList = data.map { it.mapToEntities() }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<MovieItem>> {
        return localDataSource.getFavoriteMovies().map { entityList ->
            entityList.map { it.mapToDomain() }
        }
    }

    override fun getFavoriteTvShows(): Flow<List<TvShowItem>> {
        return localDataSource.getFavoriteTvShows().map { entityList ->
            entityList.map { it.mapToDomain() }
        }
    }

    override fun setFavoriteMovie(movieItem: MovieItem, state: Boolean) {
        Executors.newSingleThreadExecutor().execute {
            localDataSource.setFavoriteMovie(
                movieItem.mapToEntities(),
                state
            )
        }
    }

    override fun setFavoriteTvShow(tvShowItem: TvShowItem, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(
                tvShowItem.mapToEntities(),
                state
            )
        }
    }
}