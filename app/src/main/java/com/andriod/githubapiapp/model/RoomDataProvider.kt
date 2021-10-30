package com.andriod.githubapiapp.model

import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import io.reactivex.Observable

class RoomDataProvider(private val db: GithubDatabase) : DataProvider() {
    override fun readUsers(): Observable<List<User>> = db.getUserDao().getUsers()
    override fun readUserRepos(user: User): Observable<List<Repo>> = db.getRepoDao().getRepos()
    override fun saveUser(user: User) = db.getUserDao().insert(user)
    override fun saveRepo(repo: Repo) = db.getRepoDao().insert(repo)
}