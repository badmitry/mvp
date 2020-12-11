package com.badmitry.github.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.badmitry.github.R
import com.badmitry.github.databinding.FragmentRepoBinding
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import com.badmitry.github.mvp.presenter.RepoPresenter
import com.badmitry.github.mvp.view.IRepoView
import com.badmitry.github.ui.App
import com.badmitry.github.ui.BackBtnListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment(): MvpAppCompatFragment(), IRepoView, BackBtnListener {

    companion object {
        private const val REPO_ARG = "repo"

        fun newInstance(repo: GithubUserRepo) = RepoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO_ARG, repo)
            }
        }
    }

    val presenter: RepoPresenter by moxyPresenter {
        val repo = arguments?.getParcelable<GithubUserRepo>(REPO_ARG) as GithubUserRepo
        RepoPresenter(repo).apply{App.component.inject(this)}
    }

    var binding: FragmentRepoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo, container, false)
        return binding?.root
    }

    override fun setRepoName(name: String) {
        binding?.tvName?.text = name
    }

    override fun setRepoIsFork(name: String) {
        binding?.tvFork?.text = name
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}