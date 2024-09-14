package com.example.common.data.util

sealed class RequestState<out T> {
    data class Success<out T>(val data: T) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()
}