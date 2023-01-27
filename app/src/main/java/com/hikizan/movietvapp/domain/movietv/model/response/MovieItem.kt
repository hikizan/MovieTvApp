package com.hikizan.movietvapp.domain.movietv.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem(
    val id: Int,
    val originalTitle: String,
    val backdropPath: String,
    val overview: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val title: String,
    val voteCount: Int,
    val posterPath: String,
    val isFavorite: Boolean
) : Parcelable
