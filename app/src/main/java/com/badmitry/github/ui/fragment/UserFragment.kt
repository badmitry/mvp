package com.badmitry.github.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.badmitry.github.R
import com.badmitry.github.databinding.FragmentUserBinding
import com.badmitry.github.mvp.model.api.ApiHolder
import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.repo.RetrofitGithubReposRepo
import com.badmitry.github.mvp.model.repo.RetrofitGithubUsersRepo
import com.badmitry.github.mvp.presenter.UserPresenter
import com.badmitry.github.mvp.view.IUserView
import com.badmitry.github.ui.App
import com.badmitry.github.ui.BackBtnListener
import com.badmitry.github.ui.adapter.ReposRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment() : MvpAppCompatFragment(), IUserView, BackBtnListener {

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(user, App.instance.router, RetrofitGithubReposRepo(ApiHolder.api), AndroidSchedulers.mainThread())
    }

    private var binding: FragmentUserBinding? = null
    private var adapter: ReposRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        return binding?.root
    }

    override fun init() {
        binding?.rvRepo?.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        binding?.rvRepo?.adapter = adapter
    }

    override fun setLogin(login: String) {
        binding?.tvLogin?.text = login
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}