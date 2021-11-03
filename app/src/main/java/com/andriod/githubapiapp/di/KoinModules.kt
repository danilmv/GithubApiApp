package com.andriod.githubapiapp.di

import androidx.room.Room
import com.andriod.githubapiapp.model.DataProvider
import com.andriod.githubapiapp.model.retrofit.GithubApi
import com.andriod.githubapiapp.model.retrofit.RetrofitDataProvider
import com.andriod.githubapiapp.model.room.GithubDatabase
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