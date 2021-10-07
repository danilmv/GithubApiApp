package com.andriod.githubapiapp.userlist

import android.os.Handler
import android.os.Looper
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.utils.postDelayed
import kotlin.random.Random

class UserItemPresenter : UserItemContract.Presenter<UserItemContract.View> {
    private var users = mutableListOf<User>()
    private val handler = Handler(Looper.getMainLooper())

    override fun onBind(view: UserItemContract.View, position: Int) {
        view.setState(UserItemContract.ViewState.LOADING)
        val sleepingTime = 1000L + Random.nextLong(1000L)
        handler.postDelayed(sleepingTime) {
            view.setUser(users[position])
            view.setState(UserItemContract.ViewState.IDLE)
        }
    }

    override fun getItemCount() = users.size

    override fun passUserList(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
    }
}