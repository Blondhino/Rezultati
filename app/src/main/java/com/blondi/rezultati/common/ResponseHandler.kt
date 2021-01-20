package com.blondi.rezultati.common



class ResponseHandler {

    fun <T : Any> handleSuccess(data: Response<T>): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(errorMessage : String): Resource<T> {
        return Resource.error()
    }

}