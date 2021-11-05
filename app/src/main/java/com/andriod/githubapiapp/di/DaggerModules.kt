package com.andriod.githubapiapp.di

import com.andriod.githubapiapp.MainActivity
import com.andriod.githubapiapp.repoDetails.RepoDetailsFragment
import com.andriod.githubapiapp.userDetails.UserDetailsFragment
import com.andriod.githubapiapp.userlist.UserListFragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RouterModule{
    @Provides
    @Singleton
    fun provideNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Singleton
    fun provideCicerone():Cicerone<Router> = Cicerone.create()
}

@Component( modules = [RouterModule::class])
@Singleton
interface AppComponent{
    fun inject(activity: MainActivity)
    fun inject(fragment: UserListFragment)
    fun inject(fragment: UserDetailsFragment)

    fun getNavigationHolder(): NavigatorHolder
}
