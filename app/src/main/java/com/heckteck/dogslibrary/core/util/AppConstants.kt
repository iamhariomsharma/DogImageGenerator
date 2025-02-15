package com.heckteck.dogslibrary.core.util

object AppConstants {

    const val DATABASE_NAME = "dog_images.db"

    object Errors {
        const val UNKNOWN = "Something went wrong, please try again later..."
        const val NO_INTERNET = "Oops, No Internet, Please check your connection."
        const val INTERNAL_SERVER_ERROR = "Internal Server Error, please try again later."
        const val JSON_PARSING_ERROR = "Json Parsing Error, please contact developer."
        const val NO_DATA_FOUND = "No Data Found!"
    }
}