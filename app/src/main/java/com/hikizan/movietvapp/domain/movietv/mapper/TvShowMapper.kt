package com.hikizan.movietvapp.domain.movietv.mapper

import com.hikizan.movietvapp.data.movietv.local.model.entity.TvShowItemEntity
import com.hikizan.movietvapp.data.movietv.remote.model.response.TvShowItemResponse
import com.hikizan.movietvapp.domain.movietv.model.response.TvShowItem
import com.hikizan.movietvapp.utils.ext.orZero

fun TvShowItemEntity.mapToDomain() =
    TvShowItem(
        id = tvShowId.orZero(),
        originalName = originalName.orEmpty(),
        backDropPath = backdropPath.orEmpty(),
        firstAirDate = firstAirDate.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        name = name.orEmpty(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmpty(),
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
        originalName = originalName.orEmpty(),
        backdropPath = backDropPath.orEmpty(),
        firstAirDate = firstAirDate.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity.orZero(),
        voteAverage = voteAverage.orZero(),
        name = name.orEmpty(),
        voteCount = voteCount.orZero(),
        posterPath = posterPath.orEmpty(),
        isFavorite = isFavorite
    )