package com.blondi.rezultati.common
import com.blondi.rezultati.common.api.Routes.API_GET_ALL_MATCHES
import com.blondi.rezultati.common.api.Routes.API_GET_SPORTS
import com.blondi.rezultati.common.model.MatchModel
import com.blondi.rezultati.common.model.Sport
import okhttp3.MultipartBody
import retrofit2.http.*

interface RezultatiApi {


    @GET(API_GET_ALL_MATCHES)
    suspend fun getAllMatches(@Path("sportId") matchId : Int):Response<ArrayList<MatchModel>>

    @GET(API_GET_SPORTS)
    suspend fun getAllSports():Response<ArrayList<Sport>>
}