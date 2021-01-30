package com.blondi.rezultati.di.module

import com.blondi.rezultati.common.repo.HomeRepo
import com.blondi.rezultati.common.repo.MatchDetailsRepo
import com.blondi.rezultati.common.repo.TeamDetailsRepo
import org.koin.dsl.module

val repoModule = module {
    single { HomeRepo(get(),get()) }
    single {MatchDetailsRepo(get(),get())}
    single {TeamDetailsRepo(get(),get())}
}