package com.andriod.githubapiapp.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "users")
@Parcelize
data class User(
    @PrimaryKey
    val login: String,
    @ColumnInfo(name = "avatar")
    @SerializedName("avatar_url")
    val avatar: String,
    @ColumnInfo(name = "repos")
    @SerializedName("repos_url")
    val repos: String,
    var rating: Int = 0,
) : Parcelable
