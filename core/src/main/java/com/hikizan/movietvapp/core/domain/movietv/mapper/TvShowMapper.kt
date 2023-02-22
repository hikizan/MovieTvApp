package com.hikizan.movietvapp.core.domain.movietv.mapper

import com.hikizan.movietvapp.core.data.movietv.local.model.entity.TvShowItemEntity
import com.hikizan.movietvapp.core.data.movietv.remote.model.response.TvShowItemResponse
import com.hikizan.movietvapp.core.domain.movietv.model.response.TvShowItem
import com.hikizan.movietvapp.core.utils.ext.orEmptyString
import com.hikizan.movietvapp.core.utils.ext.orZero

fun TvShowItemEntity.mapToDomain() =
    TvShowItem(
        id = tvShowId.orZero(),
        originalName = originalName.orEmptyString(),
        backDropPath = backdropPath.orEmptyString(),
        firstAirDate = firstAirDate.orEmptyString(),
        overview = overview.orEmptyString(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        name = name.orEmptyString(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmptyString(),
        isFavorite = isFavorite
    )

fun TvShowItemResponse.mapToEntities() =
    TvShowItemEntity(
        tvShowId = id.orZero(),
        backdropPath = backdropPath.orEmpty(),
        firstAirDate = firstAirDate.orEmpty(),
        overview = overview.orEmpty(),
        originalName = originalName.orEmpty(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        name = name.orEmpty(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmpty(),
        isFavorite = false
    )

fun TvShowItem.mapToEntities() =
    TvShowItemEntity(
        tvShowId = id.orZero(),
        originalName = originalName.orEmptyString(),
        backdropPath = backDropPath.orEmptyString(),
        firstAirDate = firstAirDate.orEmptyString(),
        overview = overview.orEmptyString(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        name = name.orEmptyString(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmptyString(),
        isFavorite = isFavorite
    )