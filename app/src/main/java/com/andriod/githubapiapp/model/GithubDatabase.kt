package com.andriod.githubapiapp.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User

@Database(entities = [User::class, Repo::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getRepoDao(): RepoDao
}