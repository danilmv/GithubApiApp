package com.andriod.githubapiapp.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andriod.githubapiapp.databinding.ItemUserBinding
import com.andriod.githubapiapp.entity.User
import com.bumptech.glide.Glide

class UserListAdapter(private val listener: Listener) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private var users = mutableListOf<User>()

    fun interface Listener {
        fun onClick(user: User)
    }

    class ViewHolder(parent: ViewGroup, listener: Listener) :
        RecyclerView.ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).root
        ) {
        private var binding: ItemUserBinding = ItemUserBinding.bind(itemView)
        private lateinit var user: User

        init {
            binding.root.setOnClickListener {
                listener.onClick(user)
            }
        }

        fun onBind(user: User) {
            binding.loginTextView.text = user.login
            if (user.rating != 0) binding.ratingTextView.text = "(${user.rating})"
            this.user = user

            Glide.with(binding.root)
                .load(user.avatar)
                .centerCrop()
                .into(binding.avatarImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent, listener)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(users[position])

    override fun getItemCount() = users.size
    fun setData(users: List<User>?) {
        users?.let {
            this.users.clear()
            this.users.addAll(users)
            notifyDataSetChanged()
        }
    }
}