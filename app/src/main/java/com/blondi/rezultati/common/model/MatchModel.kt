package com.blondi.rezultati.common.model

import com.google.gson.annotations.SerializedName

data class MatchModel(
        val id : Int?,
        val sport_id : Int?,
        val team_one : Int?,
        val team_two : Int?,
        val sport : Sport?,
        val teamOne: Team?,
        val teamTwo: Team?,
        val score : String?,
        val events : ArrayList<Event>
)