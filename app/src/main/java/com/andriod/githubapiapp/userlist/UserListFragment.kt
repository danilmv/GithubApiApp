package com.andriod.githubapiapp.userlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.andriod.githubapiapp.databinding.FragmentUserListBinding
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.utils.app
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserListFragment : MvpAppCompatFragment(), UserListContract.View {
    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding get() = _binding!!

    private val adapter by lazy { UserListAdapter { user -> presenter.onItemCLick(user) } }
    private val presenter by moxyPresenter { UserListPresenter(requireContext().app.dataProvider) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView() called")
        _binding = FragmentUserListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated() called")
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
        }
    }

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach() called")
        super.onAttach(context)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy() called")
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "@@UserListFragment"
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
}