package com.hikizan.movietvapp.core.data.movietv.remote.model.response


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)