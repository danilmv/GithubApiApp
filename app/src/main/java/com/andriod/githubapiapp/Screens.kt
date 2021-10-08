package com.andriod.githubapiapp

import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.userDetails.UserDetailsFragment
import com.andriod.githubapiapp.userlist.UserListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun UserList() = FragmentScreen { UserListFragment() }
    fun UserDetails(user: User) = FragmentScreen { UserDetailsFragment.newInstance(user) }
}