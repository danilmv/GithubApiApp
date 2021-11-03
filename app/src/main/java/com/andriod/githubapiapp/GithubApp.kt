package com.andriod.githubapiapp

import android.app.Application
import com.andriod.githubapiapp.di.combineModule
import com.andriod.githubapiapp.di.retrofitModule
import com.andriod.githubapiapp.di.roomModule
import com.andriod.githubapiapp.model.*
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubApp : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GithubApp)
            modules(retrofitModule, roomModule, combineModule)
        }
    }
}