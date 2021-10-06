package com.andriod.githubapiapp

import android.app.Application
import com.andriod.githubapiapp.model.DataProvider
import com.andriod.githubapiapp.model.DummyDataProvider

class GithubApp:Application() {
    val dataProvider: DataProvider by lazy { DummyDataProvider() }
}