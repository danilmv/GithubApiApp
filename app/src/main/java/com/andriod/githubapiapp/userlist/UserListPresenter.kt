package com.andriod.githubapiapp.userlist

import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.model.DataProvider

class UserListPresenter(private val dataProvider: DataProvider) :
    UserListContract.Presenter() {

    private var subscription = {}
    override fun onItemCLick(user: User) {
        viewState.showUserDetails(user)
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