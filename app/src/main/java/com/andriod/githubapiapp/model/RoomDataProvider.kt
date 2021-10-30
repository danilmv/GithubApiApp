package com.andriod.githubapiapp.model

import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import io.reactivex.Observable

class RoomDataProvider(private val db: GithubDatabase) : DataProvider() {
    override fun readUsers(): Observable<List<User>> = db.getUserDao().getUsers()

    override fun readUserRepos(user: User): Observable<List<Repo>> = db.getRepoDao().getRepos()
}