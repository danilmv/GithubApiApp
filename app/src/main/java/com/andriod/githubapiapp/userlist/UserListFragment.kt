package com.andriod.githubapiapp.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.andriod.githubapiapp.GithubApp
import com.andriod.githubapiapp.databinding.FragmentUserListBinding
import com.andriod.githubapiapp.entity.User
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get
import javax.inject.Inject

class UserListFragment : MvpAppCompatFragment(), UserListContract.View {
    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding get() = _binding!!

    init {
        GithubApp.instance.appComponent.inject(this)
    }

    @Inject
    lateinit var router: Router

    private val adapter by lazy { UserListAdapter { user -> presenter.onItemCLick(user) } }
    private val presenter by moxyPresenter {
        UserListPresenter(
            get(),
            router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setState(state: UserListContract.ViewState) {
        binding.root.children.forEach { it.isVisible = false }
        when (state) {
            UserListContract.ViewState.IDLE -> binding.recyclerView.isVisible = true
            UserListContract.ViewState.LOADING -> binding.progressBar.isVisible = true
        }
    }

    override fun setData(users: List<User>) {
        adapter.setData(users)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(
            requireContext(),
            "We have an error: ${throwable.message}",
            Toast.LENGTH_LONG
        ).show()
    }
}