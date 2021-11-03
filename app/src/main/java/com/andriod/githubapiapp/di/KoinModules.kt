package com.andriod.githubapiapp.di

import androidx.room.Room
import com.andriod.githubapiapp.model.CombineDataProvider
import com.andriod.githubapiapp.model.DataProvider
import com.andriod.githubapiapp.model.retrofit.GithubApi
import com.andriod.githubapiapp.model.retrofit.RetrofitDataProvider
import com.andriod.githubapiapp.model.room.GithubDatabase
import com.github.terrakok.cicerone.BaseRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BODY }
            ).build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(GithubApi::class.java)
    }

    single<DataProvider>(named("web")) { RetrofitDataProvider(get()) }
}

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

val routerModule = module {
    single { Cicerone.create() }
    single { get<Cicerone<BaseRouter>>().router as Router }
    single { get<Cicerone<BaseRouter>>().getNavigatorHolder() }
}