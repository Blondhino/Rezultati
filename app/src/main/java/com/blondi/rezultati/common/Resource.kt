package com.blondi.rezultati.common

data class Resource<out T>(var status: Status, var message: String?){

    companion object{

        fun <T> success(): Resource<T> =
            Resource(
                Status.SUCCESS,
                null

            )

        fun <T> error(msg: String): Resource<T> =
            Resource(
                Status.ERROR,
                msg
            )

        fun <T> loading(data: T?): Resource<T> =
            Resource(
                Status.LOADING,
                null
            )

    }
}