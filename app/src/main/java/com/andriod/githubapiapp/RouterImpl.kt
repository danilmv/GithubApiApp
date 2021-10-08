package com.andriod.githubapiapp

import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.userDetails.UserDetailsFragment
import com.andriod.githubapiapp.userlist.UserListFragment

class RouterImpl : Router {
    override fun showUserList(activity: MainActivity) {
        activity.supportFragmentManager.beginTransaction()
            .replace(activity.binding.container.id, UserListFragment())
            .commit()
    }

    override fun showUserDetails(activity: MainActivity, user: User) {
        activity.supportFragmentManager.beginTransaction()
            .replace(activity.binding.container.id, UserDetailsFragment.newInstance(user))
            .commit()
    }
}