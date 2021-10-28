package com.andriod.githubapiapp

import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.repoDetails.RepoDetailsFragment
import com.andriod.githubapiapp.userDetails.UserDetailsFragment
import com.andriod.githubapiapp.userlist.UserListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun UserList() = FragmentScreen { UserListFragment() }
    fun UserDetails(user: User) = FragmentScreen { UserDetailsFragment.newInstance(user) }
    fun RepoDetails(repo: Repo) = FragmentScreen { RepoDetailsFragment.newInstance(repo) }
}