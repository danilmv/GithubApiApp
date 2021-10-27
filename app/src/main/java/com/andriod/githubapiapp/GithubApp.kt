package com.andriod.githubapiapp

import android.app.Application
import com.andriod.githubapiapp.model.DataProvider
import com.andriod.githubapiapp.model.GithubApi
import com.andriod.githubapiapp.model.RetrofitDataProvider
import com.github.terrakok.cicerone.Cicerone
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GithubApp : Application() {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    private val service: GithubApi by lazy { retrofit.create(GithubApi::class.java) }
    val dataProvider: DataProvider by lazy { RetrofitDataProvider(service) }
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
}