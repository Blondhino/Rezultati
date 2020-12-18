package com.blondi.rezultati.ui.home.data

import com.blondi.rezultati.common.ObservableViewModel
import com.blondi.rezultati.common.repo.HomeRepo


class HomeViewModel(private val homeRepo: HomeRepo):ObservableViewModel(){

    fun fetchMatches(){
        homeRepo.fetchMatches()
    }

}