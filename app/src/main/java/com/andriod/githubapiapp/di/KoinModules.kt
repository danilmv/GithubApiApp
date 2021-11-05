package com.andriod.githubapiapp.di

import androidx.room.Room
import com.andriod.githubapiapp.model.CombineDataProvider
import com.andriod.githubapiapp.model.DataProvider
import com.andriod.githubapiapp.model.retrofit.RetrofitDataProvider
import com.andriod.githubapiapp.model.room.GithubDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val roomModule = module {
    single<GithubDatabase> {
        Room.databaseBuilder(
            get(),
            GithubDatabase::class.java,
            "github.db"
        ).build()
    }

    single<DataProvider>(named("local")) { RetrofitDataProvider(get()) }
}

val combineModule = module {
    single<DataProvider> {
        CombineDataProvider(
            get(named("web")),
            get(named("local"))
        )
    }
}
