package com.badmitry.github.mvp.presenter

import android.util.Log
import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.repo.GithubUsersRepo
import com.badmitry.github.mvp.presenter.list.IUserListPresenter
import com.badmitry.github.mvp.view.IUsersView
import com.badmitry.github.mvp.view.list.IUserItemView
import com.badmitry.github.navigation.Screens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<IUsersView>() {
    class UserListPresenter : IUserListPresenter {
        var users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        usersRepo.create().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
        subscribe() {
            loadData(it)
            Log.d("!!!", "onFirstViewAttach: ")
        }
        userListPresenter.itemClickListener = {itemView ->
            router.navigateTo(Screens.UserScreen(userListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData(users: MutableList<GithubUser>) {
        userListPresenter.users = users
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}