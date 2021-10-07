package com.andriod.githubapiapp.userlist

import com.andriod.githubapiapp.entity.User

class UserListContract {
    enum class ViewState{
        IDLE, LOADING
    }
    interface View{
        fun setState(state: ViewState)
        fun setData(users: List<User>)
    }

    interface Presenter<V:View>{
        fun onAttach(view: V)
        fun odDetach()
    }
}