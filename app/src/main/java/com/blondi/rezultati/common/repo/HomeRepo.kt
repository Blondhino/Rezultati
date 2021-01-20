package com.blondi.rezultati.common.repo

import com.blondi.rezultati.common.Resource
import com.blondi.rezultati.common.ResponseHandler
import com.blondi.rezultati.common.RezultatiApi
import com.blondi.rezultati.common.model.MatchModel
import com.blondi.rezultati.common.model.Sport

class HomeRepo(
    private val api : RezultatiApi,
    private val responseHandler: ResponseHandler
){

    suspend fun fetchMatches(id: Int): Resource<ArrayList<MatchModel>>{
        return try {
            val response = api.getAllMatches(id)
            return responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleException(e.localizedMessage)
        }
    }


    suspend fun fetchSports(): Resource<ArrayList<Sport>>{
        return try {
            val response = api.getAllSports()
            return responseHandler.handleSuccess(response)
        }catch (e:Exception){
            responseHandler.handleException(e.localizedMessage)
        }
    }
}