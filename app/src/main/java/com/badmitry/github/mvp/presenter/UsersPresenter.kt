package com.badmitry.github.mvp.presenter

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.repo.IGithubUsersRepo
import com.badmitry.github.mvp.presenter.list.IUserListPresenter
import com.badmitry.github.mvp.view.IUsersView
import com.badmitry.github.mvp.view.list.IUserItemView
import com.badmitry.github.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter : MvpPresenter<IUsersView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var uiSchedulers: Scheduler

    @Inject
    lateinit var usersRepoI: IGithubUsersRepo

    class UserListPresenter : IUserListPresenter {
        var users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val userListPresenter = UserListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        userListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.UserScreen(userListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData() {
        usersRepoI.getUsers().observeOn(uiSchedulers).subscribe({ repos ->
            userListPresenter.users.clear()
            userListPresenter.users.addAll(repos)
            viewState.updateList()
        }, {
            println("Error: ${it.message}")
        }).addTo(compositeDisposable)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}