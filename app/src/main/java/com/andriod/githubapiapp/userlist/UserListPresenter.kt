package com.andriod.githubapiapp.userlist

import com.andriod.githubapiapp.Screens
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.model.DataProvider
import com.github.terrakok.cicerone.Router

class UserListPresenter(
    private val dataProvider: DataProvider,
    private val router: Router
) : UserListContract.Presenter() {

    private var subscription = {}
    override fun onItemCLick(user: User) {
        router.navigateTo(Screens.UserDetails(user))
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setState(UserListContract.ViewState.LOADING)
        dataProvider.apply {
            subscription = {
                viewState.setState(UserListContract.ViewState.IDLE)
                viewState.setData(users)
            }
            subscribe(subscription)
            readData()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dataProvider.unSubscribe(subscription)
    }
}