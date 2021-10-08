package com.andriod.githubapiapp.userDetails

class UserDetailsPresenter: UserDetailsContract.Presenter() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun onClose() {
        viewState.exit()
    }
}