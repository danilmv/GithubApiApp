package com.andriod.githubapiapp.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.andriod.githubapiapp.entity.User
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Observable<List<User>>

    @Insert(onConflict = IGNORE)
    fun insert(users: List<User>): Completable
}