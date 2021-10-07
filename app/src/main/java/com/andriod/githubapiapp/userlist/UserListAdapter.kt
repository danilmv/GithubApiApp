package com.andriod.githubapiapp.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.andriod.githubapiapp.databinding.ItemUserBinding
import com.andriod.githubapiapp.entity.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private val presenter by lazy { UserItemPresenter() }

    class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).root
        ), UserItemContract.View {
        private var binding: ItemUserBinding = ItemUserBinding.bind(itemView)

        override fun setState(state: UserItemContract.ViewState) {
            binding.root.children.forEach { it.isVisible = false }
            when (state) {
                UserItemContract.ViewState.IDLE -> binding.itemContainer.isVisible = true
                UserItemContract.ViewState.LOADING -> binding.progressBar.isVisible = true
            }
        }

        override fun setUser(user: User) {
            binding.loginTextView.text = user.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.onBind(holder, position)

    override fun getItemCount() = presenter.getItemCount()
    fun setData(users: List<User>?) {
        users?.let {
            presenter.passUserList(users)
            notifyDataSetChanged()
        }
    }
}