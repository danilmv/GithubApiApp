package com.andriod.githubapiapp

import android.app.Application
import com.andriod.githubapiapp.model.DataProvider
import com.andriod.githubapiapp.model.DummyDataProvider
import com.github.terrakok.cicerone.Cicerone

class GithubApp : Application() {
    val dataProvider: DataProvider by lazy { DummyDataProvider() }
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
}