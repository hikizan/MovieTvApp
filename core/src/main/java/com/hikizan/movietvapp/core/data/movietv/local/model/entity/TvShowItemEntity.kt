package com.hikizan.movietvapp.core.data.movietv.local.model.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvShowItem")
data class TvShowItemEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "originalName")
    var originalName: String,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
