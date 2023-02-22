package com.hikizan.movietvapp.core.data.movietv.remote.network

sealed class ApiResponseResult<out R> {
    data class Success<out T>(val data: T) : ApiResponseResult<T>()
    data class Error(val errorMessage: String) : ApiResponseResult<Nothing>()
    object Empty : ApiResponseResult<Nothing>()
}
