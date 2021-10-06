package com.andriod.githubapiapp.userlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andriod.githubapiapp.databinding.FragmentUserListBinding
import com.andriod.githubapiapp.utils.app

class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding get() = _binding!!

    private val adapter by lazy { UserListAdapter() }
    private var subscription = { }

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
            progressBar.isVisible = true
            recyclerView.isVisible = false

            recyclerView.layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
        }

        view.context.app.dataProvider.apply {
            readData()
            subscription = {
                adapter.setData(users)
                binding.progressBar.isVisible = false
                binding.recyclerView.isVisible = true
            }
            subscribe(subscription)
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
        requireContext().app.dataProvider.unSubscribe(subscription)
    }

    companion object {
        const val TAG = "@@UserListFragment"
    }
}