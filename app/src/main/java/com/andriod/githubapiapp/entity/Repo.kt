package com.andriod.githubapiapp.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "repos")
@Parcelize
data class Repo(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "forks_count")
    @SerializedName("forks_count")
    val forksCount: Int,
) : Parcelable