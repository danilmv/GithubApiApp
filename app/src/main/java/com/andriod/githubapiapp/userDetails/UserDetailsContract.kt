package com.andriod.githubapiapp.userDetails

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

class UserDetailsContract {
    enum class ViewState {
        IDLE, LOADING
    }

    interface View : MvpView {
        @AddToEndSingle
        fun setState(state: ViewState)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun onClose()
    }
}