package com.andriod.githubapiapp.model

import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import io.reactivex.Observable

class CombineDataProvider(
    private val webDataProvider: DataProvider,
    private val localDataProvider: DataProvider
) : DataProvider() {
    override fun readUsers(): Observable<List<User>> = webDataProvider.readUsers()

    override fun readUserRepos(user: User): Observable<List<Repo>> =
        webDataProvider.readUserRepos(user)


    override fun saveUser(user: User) {
        localDataProvider.saveUser(user)
    }

    override fun saveRepo(repo: Repo) {
        localDataProvider.saveRepo(repo)
    }
}