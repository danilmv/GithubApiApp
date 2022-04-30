package com.andriod.githubapiapp

import android.app.Application
import com.andriod.githubapiapp.di.AppComponent
import com.andriod.githubapiapp.di.DaggerAppComponent
import com.andriod.githubapiapp.di.RoomModule

class GithubApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .roomModule(RoomModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: GithubApp
            private set
    }
}