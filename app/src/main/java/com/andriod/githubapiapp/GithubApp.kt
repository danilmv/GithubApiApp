package com.andriod.githubapiapp

import android.app.Application
import com.andriod.githubapiapp.model.DataProvider
import com.andriod.githubapiapp.model.GithubApi
import com.andriod.githubapiapp.model.RetrofitDataProvider
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.terrakok.cicerone.Cicerone
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GithubApp : Application() {
    private val okHttp by lazy {
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor ())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttp)
            .build()
    }
    private val service: GithubApi by lazy { retrofit.create(GithubApi::class.java) }
    val dataProvider: DataProvider by lazy { RetrofitDataProvider(service) }
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}