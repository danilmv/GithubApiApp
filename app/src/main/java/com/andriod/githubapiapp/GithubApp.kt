package com.andriod.githubapiapp

import android.app.Application
import androidx.room.Room
import com.andriod.githubapiapp.model.*
import com.andriod.githubapiapp.model.retrofit.GithubApi
import com.andriod.githubapiapp.model.retrofit.RetrofitDataProvider
import com.andriod.githubapiapp.model.room.GithubDatabase
import com.andriod.githubapiapp.model.room.RoomDataProvider
import com.github.terrakok.cicerone.Cicerone
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GithubApp : Application() {
    private val okHttp by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
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
    private val webDataProvider: DataProvider by lazy { RetrofitDataProvider(service) }

    private val dataBase: GithubDatabase by lazy {
        Room.databaseBuilder(
            this,
            GithubDatabase::class.java,
            "github.db"
        ).build()
    }
    private val localDataProvider: DataProvider by lazy { RoomDataProvider(dataBase) }

    val dataProvider: DataProvider by lazy {
        CombineDataProvider(
            webDataProvider,
            localDataProvider
        )
    }
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
    }
}