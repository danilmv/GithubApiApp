package com.andriod.githubapiapp.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andriod.githubapiapp.entity.Repo
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface RepoDao {
    @Query("SELECT * FROM repos WHERE user = :user")
    fun getRepos(user: String): Observable<List<Repo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(repos: List<Repo>): Completable
}