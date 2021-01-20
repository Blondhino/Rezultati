package com.blondi.rezultati.common

data class Resource<out T>(var status: Status, val data: T?){

    companion object{

        fun <T> success(response: Response<T>?): Resource<T> =
            Resource(
                Status.SUCCESS,
                response?.data
            )

        fun <T> error(): Resource<T> =
            Resource(
                Status.ERROR,
                null
            )

        fun <T> loading(data: T?): Resource<T> =
            Resource(
                Status.LOADING,
                data
            )

    }
}