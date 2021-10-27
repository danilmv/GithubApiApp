package com.andriod.githubapiapp.userDetails

import com.andriod.githubapiapp.entity.Repo
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

class UserDetailsContract {
    enum class ViewState {
        IDLE, LOADING
    }

    interface View : MvpView {
        @AddToEndSingle
        fun setState(state: ViewState)

        @AddToEndSingle
        fun setData(repos: List<Repo>)

        @Skip
        fun showError(throwable: Throwable)
    }

    abstract class Presenter() : MvpPresenter<View>() {
        abstract fun onClose()
        abstract fun onItemCLick(repo: Repo)
    }
}