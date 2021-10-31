package com.andriod.githubapiapp.model

import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import io.reactivex.Observable

class CombineDataProvider(
    private val webDataProvider: DataProvider,
    private val localDataProvider: DataProvider
) : DataProvider() {
    override fun readUsers(): Observable<List<User>> =
        localDataProvider.readUsers()
            .mergeWith(webDataProvider.readUsers()
                .flatMap {
                    localDataProvider.saveUsers(it).andThen(Observable.just(it))
                })

    override fun readUserRepos(user: User): Observable<List<Repo>> =
        localDataProvider.readUserRepos(user)
            .mergeWith(webDataProvider.readUserRepos(user)
                .flatMap {
                    localDataProvider.saveRepos(it).andThen(Observable.just(it))
                })

    override fun saveUsers(users: List<User>) = localDataProvider.saveUsers(users)


    override fun saveRepos(repos: List<Repo>) = localDataProvider.saveRepos(repos)

}