package com.hikizan.movietvapp.core.data.movietv

import com.hikizan.movietvapp.core.data.movietv.Resource.Error
import com.hikizan.movietvapp.core.data.movietv.Resource.Loading
import com.hikizan.movietvapp.core.data.movietv.Resource.Success
import com.hikizan.movietvapp.core.data.movietv.remote.network.ApiResponseResult
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Loading())
            when (val apiResponseResult = createCall().first()) {
                is ApiResponseResult.Success -> {
                    saveCallResult(apiResponseResult.data)
                    emitAll(loadFromDB().map { Success(it) })
                }
                is ApiResponseResult.Empty -> {
                    emitAll(loadFromDB().map { Success(it) })
                }
                is ApiResponseResult.Error -> {
                    onFetchFailed()
                    emit(Error<ResultType>(apiResponseResult.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponseResult<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}