package com.hikizan.movietvapp.data.movietv

import com.hikizan.movietvapp.data.movietv.local.LocalDataSource
import com.hikizan.movietvapp.data.movietv.remote.RemoteDataSource
import com.hikizan.movietvapp.data.movietv.remote.model.response.MovieItemResponse
import com.hikizan.movietvapp.data.movietv.remote.model.response.TvShowItemResponse
import com.hikizan.movietvapp.data.movietv.remote.network.ApiResponseResult
import com.hikizan.movietvapp.domain.movietv.mapper.mapToDomain
import com.hikizan.movietvapp.domain.movietv.mapper.mapToEntities
import com.hikizan.movietvapp.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.domain.movietv.model.response.TvShowItem
import com.hikizan.movietvapp.domain.movietv.repositoryimpl.MovieTvRepositoryImpl
import com.hikizan.movietvapp.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map

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
                true

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

            override fun shouldFetch(data: List<TvShowItem>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponseResult<List<TvShowItemResponse>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveCallResult(data: List<TvShowItemResponse>) {
                val tvShowList = data.map { it.mapToEntities() }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()

}