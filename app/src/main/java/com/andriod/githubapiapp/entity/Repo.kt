package com.andriod.githubapiapp.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    val name: String,
    val description: String,
    val forks_count: Int,
) : Parcelable