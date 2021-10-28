package com.andriod.githubapiapp.userDetails

import com.andriod.githubapiapp.Screens
import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.model.DataProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserDetailsPresenter(
    private val router: Router,
    private val user: User,
    private val dataProvider: DataProvider
) :
    UserDetailsContract.Presenter() {

    private var disposable: Disposable? = null
        set(value) {
            field?.takeIf { !it.isDisposed }?.dispose()
            field = value
        }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setState(UserDetailsContract.ViewState.LOADING)

        disposable = dataProvider.readUserRepos(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                viewState.setState(UserDetailsContract.ViewState.IDLE)
                viewState.setData(repos)
            }, { throwable ->
                viewState.setState(UserDetailsContract.ViewState.IDLE)
                viewState.showError(throwable)
            })
    }

    override fun onClose() {
        router.backTo(Screens.UserList())
        disposable = null
    }

    override fun onItemCLick(repo: Repo) {
        router.navigateTo(Screens.RepoDetails(repo))
    }
}