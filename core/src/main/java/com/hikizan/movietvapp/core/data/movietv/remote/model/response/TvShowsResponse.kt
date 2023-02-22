package com.hikizan.movietvapp.core.data.movietv.remote.model.response

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(

	@field:SerializedName("results")
	val results: List<TvShowItemResponse>
)

data class TvShowItemResponse(

	@field:SerializedName("backdrop_path")
	val backdropPath: String?,

	@field:SerializedName("first_air_date")
	val firstAirDate: String?,

	@field:SerializedName("overview")
	val overview: String?,

	@field:SerializedName("original_name")
	val originalName: String?,

	@field:SerializedName("popularity")
	val popularity: Double?,

	@field:SerializedName("vote_average")
	val voteAverage: Double?,

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("id")
	val id: Int?,

	@field:SerializedName("vote_count")
	val voteCount: Int?,

	@field:SerializedName("poster_path")
	val posterPath: String?
)