package com.blondi.rezultati.di.module
import com.blondi.rezultati.ui.home.data.HomeViewModel
import com.blondi.rezultati.ui.matchDetails.data.MatchDetailsViewModel
import com.blondi.rezultati.ui.teamDetails.TeamDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { MatchDetailsViewModel(get()) }
    viewModel { TeamDetailsViewModel(get()) }
}
