package com.badmitry.github.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.badmitry.github.R
import com.badmitry.github.databinding.FragmentUserBinding
import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.presenter.UserPresenter
import com.badmitry.github.mvp.view.IUserView
import com.badmitry.github.ui.App
import com.badmitry.github.ui.BackBtnListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment(private val user: GithubUser): MvpAppCompatFragment(), IUserView, BackBtnListener {

    companion object {
        fun newInstance(user: GithubUser) = UserFragment(user)
    }

    val presenter: UserPresenter by moxyPresenter {
        UserPresenter(user, App.instance.router)
    }

    private lateinit var binding: FragmentUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        return binding.root
    }

    override fun init(login: String) {
        binding.tvLogin.text = login
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}