package com.andriod.githubapiapp

import com.andriod.githubapiapp.entity.User

interface Router {
    fun showUserList(activity: MainActivity)
    fun showUserDetails(activity: MainActivity, user: User)
}