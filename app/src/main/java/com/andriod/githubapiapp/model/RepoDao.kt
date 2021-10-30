package com.andriod.githubapiapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andriod.githubapiapp.entity.Repo
import io.reactivex.Observable

@Dao
interface RepoDao {
    @Query("SELECT * FROM repos")
    fun getRepos(): Observable<List<Repo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(repo: Repo)
}