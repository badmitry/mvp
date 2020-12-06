package com.badmitry.github.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.badmitry.github.R
import com.badmitry.github.databinding.FragmentListUsersBinding
import com.badmitry.github.mvp.model.api.ApiHolder
import com.badmitry.github.mvp.model.entity.room.RoomGithubUserCache
import com.badmitry.github.mvp.model.entity.room.database.Database
import com.badmitry.github.mvp.model.repo.RetrofitGithubUsersRepo
import com.badmitry.github.mvp.presenter.UsersPresenter
import com.badmitry.github.mvp.view.IUsersView
import com.badmitry.github.ui.App
import com.badmitry.github.ui.BackBtnListener
import com.badmitry.github.ui.adapter.UserRVAdapter
import com.badmitry.github.ui.image.GlideImageLoader
import com.badmitry.github.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), IUsersView, BackBtnListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(RetrofitGithubUsersRepo(ApiHolder.api,
            AndroidNetworkStatus(requireContext()),
            RoomGithubUserCache(Database.getInstance())),
            App.instance.router,
            AndroidSchedulers.mainThread())
    }

    private var binding: FragmentListUsersBinding? = null

    private var adapter: UserRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_users, container, false)
        return binding?.root
    }

    override fun init() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UserRVAdapter(presenter.userListPresenter, GlideImageLoader())
        binding?.rvUsers?.adapter = adapter
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