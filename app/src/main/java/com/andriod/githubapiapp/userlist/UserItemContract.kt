package com.andriod.githubapiapp.userlist

import com.andriod.githubapiapp.entity.User

class UserItemContract {
    enum class ViewState{
        IDLE, LOADING
    }
    interface View{
        fun setState(state:ViewState)
        fun setUser(user:User)
    }

    interface Presenter<V:View>{
        fun onBind(view: View, position: Int)
        fun getItemCount(): Int
        fun passUserList(users: List<User>)
    }
}