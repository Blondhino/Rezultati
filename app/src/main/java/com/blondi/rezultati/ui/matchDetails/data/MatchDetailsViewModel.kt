package com.blondi.rezultati.ui.matchDetails.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blondi.rezultati.common.ObservableViewModel
import com.blondi.rezultati.common.Status
import com.blondi.rezultati.common.model.MatchModel
import com.blondi.rezultati.common.repo.HomeRepo
import com.blondi.rezultati.common.repo.MatchDetailsRepo
import kotlinx.coroutines.launch


class MatchDetailsViewModel(private val repo: MatchDetailsRepo) : ObservableViewModel() {
    val match = MutableLiveData<MatchModel>()

    fun getSingleMatchDetails(matchId: Int) = viewModelScope.launch {
        Log.d("fetchingDetails","called with id: $matchId")
        val resp =repo.getSingleMatchDetails(matchId)
        if(resp.status==Status.SUCCESS){
            Log.d("fetchingDetails","success")
            match.value=resp.data
        }else if(resp.status==Status.ERROR){
            Log.d("fetchingDetails","error")
        }

    }
}