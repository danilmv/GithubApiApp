package com.andriod.githubapiapp.userDetails

import com.andriod.githubapiapp.Screens
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.eventbus.EventBus
import com.andriod.githubapiapp.utils.EventDislike
import com.andriod.githubapiapp.utils.EventLike
import com.github.terrakok.cicerone.Router

class UserDetailsPresenter(private val router: Router, private val user: User) :
    UserDetailsContract.Presenter() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setState(UserDetailsContract.ViewState.IDLE)
    }

    override fun onClose() {
        router.backTo(Screens.UserList())
    }

    override fun onLike() {
        EventBus.post(EventLike(user))
    }

    override fun onDislike() {
        EventBus.post(EventDislike(user))
    }
}