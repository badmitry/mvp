package com.badmitry.github.mvp.presenter

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.view.IUserView
import com.badmitry.github.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(private val user: GithubUser, private val router: Router) :
MvpPresenter<IUserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }

    fun backPressed(): Boolean {
        router.backTo(Screens.UsersScreen())
        return true
    }
}