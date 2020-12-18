package com.blondi.rezultati.core

import android.app.Application
import com.blondi.rezultati.di.module.networkModule
import com.blondi.rezultati.di.module.repoModule
import com.blondi.rezultati.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RezultatiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() = startKoin{
    androidContext(this@RezultatiApp)
        modules(
            viewModelModule,
            repoModule,
            networkModule
        )
    }
}