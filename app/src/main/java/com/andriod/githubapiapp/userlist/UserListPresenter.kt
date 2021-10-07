package com.andriod.githubapiapp.userlist

import com.andriod.githubapiapp.model.DataProvider

class UserListPresenter(private val dataProvider: DataProvider) :
    UserListContract.Presenter<UserListContract.View> {
    private var view: UserListContract.View? = null
    private var subscription = {}

    override fun onAttach(view: UserListContract.View) {
        this.view = view
        view.setState(UserListContract.ViewState.LOADING)
        dataProvider.apply {
            subscription = {
                view.setState(UserListContract.ViewState.IDLE)
                view.setData(users)
            }
            subscribe(subscription)
            readData()
        }
    }

    override fun odDetach() {
        view = null
        dataProvider.unSubscribe(subscription)
    }
}