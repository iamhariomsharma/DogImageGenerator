package com.heckteck.dogslibrary.core.network.util

sealed class DataResult<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T, val message: String? = null) : DataResult<T>(data)
    class Error<T>(message: String, data: T? = null) : DataResult<T>(data, message)
}