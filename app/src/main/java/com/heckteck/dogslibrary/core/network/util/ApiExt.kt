package com.heckteck.dogslibrary.core.network.util

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.heckteck.dogslibrary.core.model.ErrorResponse
import com.heckteck.dogslibrary.core.util.AppConstants
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> executeApi(call: suspend () -> Response<T>): DataResult<T> {
    val response = try {
        call.invoke()
    } catch (e: UnknownHostException) {
        return DataResult.Error(AppConstants.Errors.NO_INTERNET)
    } catch (e: IOException) {
        return DataResult.Error(AppConstants.Errors.NO_INTERNET)
    } catch (e: SocketTimeoutException) {
        return DataResult.Error(AppConstants.Errors.NO_INTERNET)
    } catch (e: JsonParseException) {
        return DataResult.Error(AppConstants.Errors.JSON_PARSING_ERROR)
    } catch (e: Exception) {
        return DataResult.Error(AppConstants.Errors.UNKNOWN)
    }

    val body = response.body()
    val errorBody = response.errorBody()
    return try {
        if (response.isSuccessful && body != null) {
            DataResult.Success(body)
        } else if (response.code() in 500..599) {
            DataResult.Error(AppConstants.Errors.INTERNAL_SERVER_ERROR)
        } else if (errorBody != null) {
            val message = parseErrorResponse(errorBody.string())
            DataResult.Error(message)
        } else {
            return DataResult.Error(AppConstants.Errors.UNKNOWN)
        }
    } catch (e: Exception) {
        DataResult.Error(e.toString())
    }
}

private fun parseErrorResponse(errorBody: String?): String {
    return if (!errorBody.isNullOrEmpty()) {
        try {
            val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
            val errorMessage = when (val message = errorResponse?.message) {
                is String -> message
                else -> AppConstants.Errors.UNKNOWN
            }

            when (errorResponse.code) {
                in 500..599 -> AppConstants.Errors.INTERNAL_SERVER_ERROR
                in 400..499 -> errorMessage.ifEmpty { AppConstants.Errors.UNKNOWN }
                else -> AppConstants.Errors.UNKNOWN
            }

        } catch (e: Exception) {
            AppConstants.Errors.UNKNOWN
        }
    } else {
        AppConstants.Errors.UNKNOWN
    }
}