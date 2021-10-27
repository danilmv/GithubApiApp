package com.andriod.githubapiapp.userDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.andriod.githubapiapp.databinding.FragmentUserDetailsBinding
import com.andriod.githubapiapp.entity.Repo
import com.andriod.githubapiapp.entity.User
import com.andriod.githubapiapp.utils.app
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsContract.View {
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding: FragmentUserDetailsBinding get() = _binding!!

    private val user by lazy { arguments?.getParcelable<User>(EXTRA_KEY_USER) }
    private val presenter by moxyPresenter {
        UserDetailsPresenter(
            requireContext().app.router,
            user!!,
            requireContext().app.dataProvider
        )
    }

    private val adapter by lazy { RepoListAdapter() }

    override fun setState(state: UserDetailsContract.ViewState) {
        binding.root.children.forEach { it.isVisible = false }
        when (state) {
            UserDetailsContract.ViewState.IDLE -> binding.container.isVisible = true
            UserDetailsContract.ViewState.LOADING -> binding.progressBar.isVisible = true
        }
    }

    override fun setData(repos: List<Repo>) {
        adapter.setData(repos)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(context, "We have an error: ${throwable.message}", Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        user?.let {
            binding.loginTextView.text = it.login
        }
        binding.buttonClose.setOnClickListener { presenter.onClose() }

        binding.apply {
            reposListRecyclerView.layoutManager = LinearLayoutManager(view.context)
            reposListRecyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EXTRA_KEY_USER = "user"
        fun newInstance(user: User) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_KEY_USER, user)
            }
        }
    }
}