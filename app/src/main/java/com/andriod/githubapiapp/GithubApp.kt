package com.andriod.githubapiapp

import android.app.Application
import com.andriod.githubapiapp.di.combineModule
import com.andriod.githubapiapp.di.retrofitModule
import com.andriod.githubapiapp.di.roomModule
import com.andriod.githubapiapp.di.routerModule
import com.andriod.githubapiapp.model.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GithubApp)
            modules(retrofitModule, roomModule, combineModule, routerModule)
        }
    }
}