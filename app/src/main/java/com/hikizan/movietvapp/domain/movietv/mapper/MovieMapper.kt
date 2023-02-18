package com.hikizan.movietvapp.domain.movietv.mapper

import com.hikizan.movietvapp.data.movietv.local.model.entity.MovieItemEntity
import com.hikizan.movietvapp.data.movietv.remote.model.response.MovieItemResponse
import com.hikizan.movietvapp.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.utils.ext.orZero

fun MovieItemResponse.mapToEntities() =
    MovieItemEntity(
        movieId = id.orZero(),
        backdropPath = backdropPath.orEmpty(),
        overview = overview.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        title = title.orEmpty(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmpty(),
        isFavorite = false
    )

fun MovieItemResponse.mapToDomain() =
    MovieItem(
        id = id.orZero(),
        originalTitle = originalTitle.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        title = title.orEmpty(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmpty(),
        isFavorite = false
    )

fun MovieItemEntity.mapToDomain() =
    MovieItem(
        id = movieId.orZero(),
        originalTitle = originalTitle.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        title = title.orEmpty(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmpty(),
        isFavorite = isFavorite ?: false
    )

fun MovieItem.mapToEntities() =
    MovieItemEntity(
        movieId = id.orZero(),
        originalTitle = originalTitle.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        title = title.orEmpty(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmpty(),
        isFavorite = isFavorite ?: false
    )