package com.hikizan.movietvapp.core.data.movietv.remote

import android.util.Log
import com.hikizan.movietvapp.core.BuildConfig
import com.hikizan.movietvapp.core.data.movietv.remote.model.response.MovieItemResponse
import com.hikizan.movietvapp.core.data.movietv.remote.model.response.TvShowItemResponse
import com.hikizan.movietvapp.core.data.movietv.remote.network.ApiResponseResult
import com.hikizan.movietvapp.core.data.movietv.remote.network.MovieTvApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val movieTvApiClient: MovieTvApiClient) {
    
    companion object {
        private const val TAG = "RemoteDataSource"
    }
    
    suspend fun getMovies(): Flow<ApiResponseResult<List<MovieItemResponse>>> {
        return flow {
            try {
                val moviesResponse =
                    movieTvApiClient.getMovies(BuildConfig.API_KEY)

                if (moviesResponse.results != null) {
                    emit(ApiResponseResult.Success(moviesResponse.results))
                } else {
                    emit(ApiResponseResult.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponseResult.Error(e.toString()))
                Log.e(TAG, "getMovies: $e" )
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShows(): Flow<ApiResponseResult<List<TvShowItemResponse>>> {
        return flow {
            try {
                val tvShowsResponse =
                    movieTvApiClient.getTvShows(BuildConfig.API_KEY)

                if (tvShowsResponse.results.isNotEmpty()) {
                    emit(ApiResponseResult.Success(tvShowsResponse.results))
                } else {
                    emit(ApiResponseResult.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponseResult.Error(e.toString()))
                Log.e(TAG, "getTvShows: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}