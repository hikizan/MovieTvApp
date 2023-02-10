package com.hikizan.movietvapp.domain.movietv

import com.hikizan.movietvapp.data.movietv.Resource
import com.hikizan.movietvapp.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.domain.movietv.model.response.TvShowItem
import com.hikizan.movietvapp.domain.movietv.repositoryimpl.MovieTvRepositoryImpl
import kotlinx.coroutines.flow.Flow

class MovieTvInteractor(private val repository: MovieTvRepositoryImpl) : MovieTvUseCase {

    override fun getMovies(): Flow<Resource<List<MovieItem>>> {
        return repository.getMovies()
    }

    override fun getTvShows(): Flow<Resource<List<TvShowItem>>> {
        return repository.getTvShows()
    }
}