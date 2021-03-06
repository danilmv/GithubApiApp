package com.andriod.githubapiapp.model

import android.os.Handler
import android.os.HandlerThread
import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import io.reactivex.Completable
import io.reactivex.Observable

abstract class DataProvider {
    protected val users = mutableListOf<User>()

    private val handlerThread = HandlerThread("handlerThread").apply { isDaemon = true;start() }
    protected val dataHandler = Handler(handlerThread.looper)

    abstract fun readUsers(): Observable<List<User>>

    abstract fun readUserRepos(user: User): Observable<List<Repo>>

    abstract fun saveUsers(users: List<User>): Completable
    abstract fun saveRepos(repos: List<Repo>): Completable

    companion object {
        const val SLEEP_TIME = 1000L
    }
}