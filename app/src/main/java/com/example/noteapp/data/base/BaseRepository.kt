package com.example.noteapp.data.base

import com.example.noteapp.domain.utils.ResultStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend ()-> T) = flow {
        emit(ResultStatus.Loading())
        try {
            val data = request()
            emit(ResultStatus.Success(data))
        } catch (e: IOException) {
            emit(ResultStatus.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}