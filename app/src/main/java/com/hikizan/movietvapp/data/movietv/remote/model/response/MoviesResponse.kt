package com.hikizan.movietvapp.data.movietv.remote.model.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

	@SerializedName("results")
	val results: List<MovieItemResponse>? = null
)

data class MovieItemResponse(

	@SerializedName("backdrop_path")
	val backdropPath: String?,

	@SerializedName("overview")
	val overview: String?,

	@SerializedName("original_title")
	val originalTitle: String?,

	@SerializedName("release_date")
	val releaseDate: String?,

	@SerializedName("popularity")
	val popularity: Double?,

	@SerializedName("vote_average")
	val voteAverage: Double?,

	@SerializedName("id")
	val id: Int?,

	@SerializedName("title")
	val title: String?,

	@SerializedName("vote_count")
	val voteCount: Int?,

	@SerializedName("poster_path")
	val posterPath: String?
)
