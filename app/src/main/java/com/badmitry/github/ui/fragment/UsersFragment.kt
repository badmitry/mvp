package com.badmitry.github.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.badmitry.github.R
import com.badmitry.github.databinding.FragmentUsersBinding
import com.badmitry.github.mvp.model.repo.GithubUsersRepo
import com.badmitry.github.mvp.presenter.UserPresenter
import com.badmitry.github.mvp.view.IUserView
import com.badmitry.github.ui.App
import com.badmitry.github.ui.BackBtnListener
import com.badmitry.github.ui.adapter.UserRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), IUserView, BackBtnListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GithubUsersRepo(), App.instance.router)
    }

    private lateinit var binding: FragmentUsersBinding

    var adapter: UserRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        return binding.root
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UserRVAdapter(presenter.userListPresenter)
        binding.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}