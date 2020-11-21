package com.badmitry.github.mvp.presenter

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.repo.GithubUsersRepo
import com.badmitry.github.mvp.presenter.list.IUserListPresenter
import com.badmitry.github.mvp.view.IUsersView
import com.badmitry.github.mvp.view.list.IUserItemView
import com.badmitry.github.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<IUsersView>() {
    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
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
        loadData()
        userListPresenter.itemClickListener = {itemView ->
            router.navigateTo(Screens.UserScreen(userListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        userListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}