package com.andriod.githubapiapp.userlist

import com.andriod.githubapiapp.Screens
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.model.DataProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserListPresenter(
    private val dataProvider: DataProvider,
    private val router: Router
) : UserListContract.Presenter() {

    private var disposable: Disposable? = null
        set(value) {
            field?.takeIf { !it.isDisposed }?.dispose()
            field = value
        }

    override fun onItemCLick(user: User) {
        router.navigateTo(Screens.UserDetails(user))
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setState(UserListContract.ViewState.LOADING)

        disposable = dataProvider.readUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                viewState.setState(UserListContract.ViewState.IDLE)
                viewState.setData(users)
            }, { throwable ->
                viewState.setState(UserListContract.ViewState.IDLE)
                viewState.showError(throwable)
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable = null
    }
}