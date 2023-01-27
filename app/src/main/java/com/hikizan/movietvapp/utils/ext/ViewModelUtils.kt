package com.hikizan.movietvapp.utils.ext

import androidx.lifecycle.MutableLiveData
import com.hikizan.movietvapp.data.movietv.Resource
import kotlinx.coroutines.flow.Flow

suspend fun <U> proceed(outputLiveData: MutableLiveData<Resource<U>>, block: suspend () -> Flow<Resource<U>>) {
    block.invoke()
        .collect {
            outputLiveData.value = it
        }
}