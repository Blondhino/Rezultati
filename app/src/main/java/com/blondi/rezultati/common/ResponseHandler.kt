package com.blondi.rezultati.common

class ResponseHandler {

    fun <T : Any> handleSuccess(): Resource<T> {
        return Resource.success()
    }

    fun <T : Any> handleException(errorMessage : String): Resource<T> {
        return Resource.error(errorMessage)
    }

}