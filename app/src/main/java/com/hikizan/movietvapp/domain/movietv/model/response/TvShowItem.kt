package com.hikizan.movietvapp.domain.movietv.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowItem(
    val id: Int,
    val originalName: String,
    val backDropPath: String,
    val firstAirDate: String,
    val overview: String,
    val popularity: Double,
    val voteAverage: Double,
    val name: String,
    val voteCount: Int,
    val posterPath: String,
    val isFavorite: Boolean
) : Parcelable