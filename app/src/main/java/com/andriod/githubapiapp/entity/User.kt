package com.andriod.githubapiapp.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String,
    @SerializedName("repos_url")
    val repos: String,
    var rating: Int = 0,
) : Parcelable
