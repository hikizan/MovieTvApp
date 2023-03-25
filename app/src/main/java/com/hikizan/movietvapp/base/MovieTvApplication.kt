package com.hikizan.movietvapp.base

import android.app.Application
import com.hikizan.movietvapp.core.di.databaseModule
import com.hikizan.movietvapp.di.featureModule.useCaseModule
import com.hikizan.movietvapp.di.featureModule.viewModelModule
import com.hikizan.movietvapp.core.di.networkModule
import com.hikizan.movietvapp.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.NONE

class MovieTvApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(NONE)
            androidContext(this@MovieTvApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}