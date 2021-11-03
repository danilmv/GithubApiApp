package com.andriod.githubapiapp

import android.app.Application
import androidx.room.Room
import com.andriod.githubapiapp.di.retrofitModule
import com.andriod.githubapiapp.model.*
import com.andriod.githubapiapp.model.room.GithubDatabase
import com.andriod.githubapiapp.model.room.RoomDataProvider
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named

class GithubApp : Application() {
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
            get(named("web")),
            localDataProvider
        )
    }
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GithubApp)
            modules(retrofitModule)
        }
    }
}