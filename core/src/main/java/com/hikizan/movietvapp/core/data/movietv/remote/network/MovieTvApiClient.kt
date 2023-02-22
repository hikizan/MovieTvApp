package com.hikizan.movietvapp.core.data.movietv.remote.network

import com.hikizan.movietvapp.core.data.movietv.remote.model.response.MovieItemResponse
import com.hikizan.movietvapp.core.data.movietv.remote.model.response.MoviesResponse
import com.hikizan.movietvapp.core.data.movietv.remote.model.response.TvShowItemResponse
import com.hikizan.movietvapp.core.data.movietv.remote.model.response.TvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieTvApiClient {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): MoviesResponse

    @GET("discover/tv")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String
    ): TvShowsResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieItemResponse

    @GET("tv/{tv_id}")
    suspend fun getTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): TvShowItemResponse
}