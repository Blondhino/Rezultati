package com.blondi.rezultati.ui.home.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blondi.rezultati.common.ObservableViewModel
import com.blondi.rezultati.common.Status
import com.blondi.rezultati.common.model.MatchModel
import com.blondi.rezultati.common.model.Sport
import com.blondi.rezultati.common.repo.HomeRepo
import kotlinx.coroutines.launch


class HomeViewModel(private val homeRepo: HomeRepo):ObservableViewModel(){

    val matches = MutableLiveData<ArrayList<MatchModel>>()
    val sports = MutableLiveData<ArrayList<Sport>>()

    fun fetchMatches(id: Int) = viewModelScope.launch{
        val resp = homeRepo.fetchMatches(id)
        if(resp.status==Status.SUCCESS){
          Log.d("fetching","success")
            matches.value=resp.data;

        }else if(resp.status==Status.ERROR){
            Log.d("fetching","error")
        }
    }

    fun fetchSports() = viewModelScope.launch {
        val resp = homeRepo.fetchSports()
        if(resp.status==Status.SUCCESS){
            Log.d("fetchingSport","success")
            sports.value=resp.data

        }else if(resp.status==Status.ERROR){
            Log.d("fetchingSport","error")
        }
    }

}