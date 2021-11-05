package com.andriod.githubapiapp

import android.app.Application
import com.andriod.githubapiapp.di.*
import com.andriod.githubapiapp.model.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@GithubApp)
            modules(roomModule, combineModule)
        }
    }

    companion object {
        lateinit var instance: GithubApp
            private set
    }
}