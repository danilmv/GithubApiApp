package com.andriod.githubapiapp.model

import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import io.reactivex.Observable

class RetrofitDataProvider(private val service: GithubApi) : DataProvider() {
    override fun readUsers(): Observable<List<User>> = service.getUsers().toObservable()

    override fun readUserRepos(user: User): Observable<List<Repo>> =
        service.getUserRepos(user.login).toObservable()
}