package com.andriod.githubapiapp.model.retrofit

import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users")
    fun getUsers(): Single<List<User>>

    @GET("users/{user}/repos")
    fun getUserRepos(@Path("user") user: String): Observable<List<Repo>>
}