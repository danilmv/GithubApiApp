package com.andriod.githubapiapp.utils

import android.content.Context
import com.andriod.githubapiapp.GithubApp

val Context.app: GithubApp
    get() = applicationContext as GithubApp