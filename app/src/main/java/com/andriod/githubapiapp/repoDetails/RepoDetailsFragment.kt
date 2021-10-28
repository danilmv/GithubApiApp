package com.andriod.githubapiapp.repoDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andriod.githubapiapp.databinding.FragmentRepoDetailsBinding
import com.andriod.githubapiapp.entity.Repo

class RepoDetailsFragment : Fragment(), RepoDetailsContract.View {

    private var _binding: FragmentRepoDetailsBinding? = null
    private val binding: FragmentRepoDetailsBinding get() = _binding!!

    private val repo: Repo? by lazy { arguments?.getParcelable(EXTRA_KEY_REPO) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repo?.let {
            binding.textViewRepoName.text = it.name
            binding.textViewForks.text = "forks: ${it.forks_count}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_KEY_REPO = "repo"
        fun newInstance(repo: Repo) = RepoDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_KEY_REPO, repo)
            }
        }
    }
}