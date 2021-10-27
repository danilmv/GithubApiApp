package com.andriod.githubapiapp.userDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andriod.githubapiapp.databinding.ItemRepoBinding
import com.andriod.githubapiapp.entity.Repo

class RepoListAdapter(private val listener: Listener) :
    RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    private var repos = mutableListOf<Repo>()

    fun interface Listener {
        fun onClick(repo: Repo)
    }

    class ViewHolder(parent: ViewGroup, private val listener: Listener) :
        RecyclerView.ViewHolder(
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        ) {
        private var binding: ItemRepoBinding = ItemRepoBinding.bind(itemView)
        private lateinit var repo: Repo

        init {
            binding.root.setOnClickListener { listener.onClick(repo) }
        }

        fun bind(repo: Repo) {
            this.repo = repo
            binding.nameTextView.text = repo.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent, listener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount(): Int = repos.size

    fun setData(repos: List<Repo>?) {
        repos?.let {
            this.repos.clear()
            this.repos.addAll(repos)
            notifyDataSetChanged()
        }
    }
}