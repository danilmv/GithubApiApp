package com.andriod.githubapiapp.model

import com.andriod.githubapiapp.entity.User
import io.reactivex.Single
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getUsers(): Single<List<User>>
}