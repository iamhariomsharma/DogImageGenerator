package com.heckteck.dogslibrary.core.network.util

sealed class DataResult<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T, val message: String? = null) : DataResult<T>(data)
    class Error<T>(message: String, data: T? = null) : DataResult<T>(data, message)
}

inline fun <D, R> DataResult<D>.map(transform: (D?) -> R): DataResult<R> {
    return when (this) {
        is DataResult.Success -> DataResult.Success(transform(data), message)
        is DataResult.Error -> DataResult.Error(error.toString())
    }
}