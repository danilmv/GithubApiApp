package com.andriod.githubapiapp.di

import com.andriod.githubapiapp.MainActivity
import com.andriod.githubapiapp.model.DataProvider
import com.andriod.githubapiapp.model.retrofit.GithubApi
import com.andriod.githubapiapp.model.retrofit.RetrofitDataProvider
import com.andriod.githubapiapp.userDetails.UserDetailsFragment
import com.andriod.githubapiapp.userlist.UserListFragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RouterModule {
    @Provides
    @Singleton
    fun provideNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()
}

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY }
            ).build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

    @Provides
    @Singleton
    @Named("web")
    fun provideDataProvider(githubApi: GithubApi): DataProvider = RetrofitDataProvider(githubApi)
}

@Component(modules = [RouterModule::class, RetrofitModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: UserListFragment)
    fun inject(fragment: UserDetailsFragment)

    fun getNavigationHolder(): NavigatorHolder
}
