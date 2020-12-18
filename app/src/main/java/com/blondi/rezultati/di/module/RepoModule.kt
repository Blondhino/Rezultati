package com.blondi.rezultati.di.module

import com.blondi.rezultati.common.repo.HomeRepo
import org.koin.dsl.module

val repoModule = module {
    single { HomeRepo() }
}