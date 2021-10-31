package com.andriod.githubapiapp.model.room

import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.model.DataProvider
import io.reactivex.Observable

class RoomDataProvider(private val db: GithubDatabase) : DataProvider() {
    override fun readUsers(): Observable<List<User>> = db.getUserDao().getUsers()
    override fun readUserRepos(user: User): Observable<List<Repo>> =
        db.getRepoDao().getRepos(user.login)

    override fun saveUsers(users: List<User>) = db.getUserDao().insert(users)
    override fun saveRepos(repos: List<Repo>) = db.getRepoDao().insert(repos)
}