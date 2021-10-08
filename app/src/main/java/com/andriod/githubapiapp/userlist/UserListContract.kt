package com.andriod.githubapiapp.userlist

import com.andriod.githubapiapp.entity.User
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class UserListContract {
    enum class ViewState {
        IDLE, LOADING
    }

    interface View : MvpView {
        @AddToEndSingle
        fun setState(state: ViewState)

        @AddToEndSingle
        fun setData(users: List<User>)
    }

    abstract class Presenter : MvpPresenter<View>() {
    }
}