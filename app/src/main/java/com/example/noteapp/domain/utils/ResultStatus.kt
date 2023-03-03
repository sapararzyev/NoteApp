package com.example.noteapp.domain.utils

sealed class ResultStatus<T>(
    val data:T?,
    val error:String?
){
    class Loading<T>():ResultStatus<T>(data = null, error = null)
    class Success<T>( data: T?):ResultStatus<T>(data = data,null)
    class Error<T>(message: String?):ResultStatus<T>(error = message, data = null)
}

