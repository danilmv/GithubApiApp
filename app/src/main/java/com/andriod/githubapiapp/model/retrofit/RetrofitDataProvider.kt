package com.andriod.githubapiapp.model.retrofit

import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.model.DataProvider
import io.reactivex.Completable
import io.reactivex.Observable

class RetrofitDataProvider(private val service: GithubApi) : DataProvider() {
    override fun readUsers(): Observable<List<User>> = service.getUsers().toObservable()

    override fun readUserRepos(user: User): Observable<List<Repo>> =
        service.getUserRepos(user.login)
            .map { it.onEach { repo -> repo.user = user.login } }

    override fun saveUsers(users: List<User>): Completable {
        TODO("Not yet implemented")
    }

    override fun saveRepos(repos: List<Repo>): Completable {
        TODO("Not yet implemented")
    }


}