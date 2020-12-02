package com.badmitry.github.navigation

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import com.badmitry.github.ui.fragment.RepoFragment
import com.badmitry.github.ui.fragment.UserFragment
import com.badmitry.github.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class RepoScreen(private val repo: GithubUserRepo) : SupportAppScreen() {
        override fun getFragment() = RepoFragment.newInstance(repo)
    }
}