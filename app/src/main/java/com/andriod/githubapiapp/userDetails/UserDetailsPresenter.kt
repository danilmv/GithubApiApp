package com.andriod.githubapiapp.userDetails

import com.andriod.githubapiapp.Screens
import com.github.terrakok.cicerone.Router

class UserDetailsPresenter(private val router: Router) : UserDetailsContract.Presenter() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setState(UserDetailsContract.ViewState.IDLE)
    }

    override fun onClose() {
        router.backTo(Screens.UserList())
    }
}