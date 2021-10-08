package com.andriod.githubapiapp.userDetails

import com.andriod.githubapiapp.Screens
import com.github.terrakok.cicerone.Router

class UserDetailsPresenter(private val router: Router) : UserDetailsContract.Presenter() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun onClose() {
        router.navigateTo(Screens.UserList())
    }
}