package com.hikizan.movietvapp.core.domain.movietv.mapper

import com.hikizan.movietvapp.core.data.movietv.local.model.entity.MovieItemEntity
import com.hikizan.movietvapp.core.data.movietv.remote.model.response.MovieItemResponse
import com.hikizan.movietvapp.core.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.core.utils.ext.orEmptyString
import com.hikizan.movietvapp.core.utils.ext.orZero

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

fun MovieItemEntity.mapToDomain() =
    MovieItem(
        id = movieId.orZero(),
        originalTitle = originalTitle.orEmptyString(),
        backdropPath = backdropPath.orEmptyString(),
        overview = overview.orEmptyString(),
        releaseDate = releaseDate.orEmptyString(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        title = title.orEmptyString(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmptyString(),
        isFavorite = isFavorite
    )

fun MovieItem.mapToEntities() =
    MovieItemEntity(
        movieId = id.orZero(),
        originalTitle = originalTitle.orEmptyString(),
        backdropPath = backdropPath.orEmptyString(),
        overview = overview.orEmptyString(),
        releaseDate = releaseDate.orEmptyString(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        title = title.orEmptyString(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmptyString(),
        isFavorite = isFavorite
    )