package com.blondi.rezultati.ui.teamDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blondi.rezultati.common.ObservableViewModel
import com.blondi.rezultati.common.Status
import com.blondi.rezultati.common.model.MatchModel
import com.blondi.rezultati.common.model.Player
import com.blondi.rezultati.common.model.Team
import com.blondi.rezultati.common.repo.HomeRepo
import com.blondi.rezultati.common.repo.MatchDetailsRepo
import com.blondi.rezultati.common.repo.TeamDetailsRepo
import kotlinx.coroutines.launch


class TeamDetailsViewModel(private val repo: MatchDetailsRepo) : ObservableViewModel() {


    val players = MutableLiveData<ArrayList<Player>>()

    fun getSingleTeamDetails(matchId: Int, teamId: Int, teamNum:Int) = viewModelScope.launch {
        Log.d("fetchingDetails","called with match id $matchId teamId: $teamId teamNum: $teamNum")
        val resp =repo.getSingleMatchDetails(matchId)
        if(resp.status==Status.SUCCESS){
            Log.d("fetchingDetails","success")
            if (teamNum==1) {
                players.value=resp.data?.teamOne?.players
            } else {
                players.value=resp.data?.teamTwo?.players
            }
        }else if(resp.status==Status.ERROR){
            Log.d("fetchingDetails","error")
        }

    }
}