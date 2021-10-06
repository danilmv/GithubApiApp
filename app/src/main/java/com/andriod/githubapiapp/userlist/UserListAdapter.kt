package com.andriod.githubapiapp.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andriod.githubapiapp.databinding.ItemUserBinding
import com.andriod.githubapiapp.entity.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var users = mutableListOf<User>()

    class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).root
        ) {
        private var binding: ItemUserBinding = ItemUserBinding.bind(itemView)

        fun bind(user: User) {
            binding.loginTextView.text = user.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(users[position])
    override fun getItemCount() = users.size
    fun setData(users: List<User>?) {
        users?.let {
            this.users.clear()
            this.users.addAll(it)
            notifyDataSetChanged()
        }
    }
}