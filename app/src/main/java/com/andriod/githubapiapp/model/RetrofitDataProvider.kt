package com.andriod.githubapiapp.model

import com.andriod.githubapiapp.entity.User
import io.reactivex.Observable

class RetrofitDataProvider(private val service: GithubApi) : DataProvider() {
    override fun readData(): Observable<List<User>> = service.getUsers().toObservable()
}