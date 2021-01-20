package com.blondi.rezultati.common.repo

import com.blondi.rezultati.common.Resource
import com.blondi.rezultati.common.ResponseHandler
import com.blondi.rezultati.common.RezultatiApi
import com.blondi.rezultati.common.model.MatchModel

class MatchDetailsRepo(
    private val api: RezultatiApi,
    private val responseHandler: ResponseHandler
) {

    suspend fun getSingleMatchDetails(matchId: Int): Resource<MatchModel> {
        return try {
            val response = api.getSingleMatchDetails(matchId)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e.localizedMessage)
        }
    }

}