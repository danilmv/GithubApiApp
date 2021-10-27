package com.andriod.githubapiapp.userDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andriod.githubapiapp.databinding.ItemRepoBinding
import com.andriod.githubapiapp.entity.Repo

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
    private var repos = mutableListOf<Repo>()

    class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        ) {
        private var binding: ItemRepoBinding = ItemRepoBinding.bind(itemView)
        private lateinit var repo: Repo

        fun bind(repo: Repo) {
            this.repo = repo
            binding.nameTextView.text = repo.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

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