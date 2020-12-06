package com.badmitry.github.mvp.presenter

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import com.badmitry.github.mvp.model.repo.IGithubReposRepo
import com.badmitry.github.mvp.presenter.list.IReposListPresenter
import com.badmitry.github.mvp.view.IUserView
import com.badmitry.github.mvp.view.list.IReposItemView
import com.badmitry.github.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val user: GithubUser,
    private val router: Router,
    private val githubReposRepo: IGithubReposRepo,
    private var uiSchedulers: Scheduler
) :
    MvpPresenter<IUserView>() {

    class ReposListPresenter : IReposListPresenter {
        var repos = mutableListOf<GithubUserRepo>()
        override var itemClickListener: ((IReposItemView) -> Unit)? = null

        override fun bindView(view: IReposItemView) {
            val repo = repos[view.pos]
            view.setRepoName(repo.name)
        }

        override fun getCount(): Int = repos.size
    }

    val reposListPresenter = ReposListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
        viewState.init()
        loadData(user)
        reposListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.RepoScreen(reposListPresenter.repos[itemView.pos]))
        }
    }

    private fun loadData(user: GithubUser) {
        githubReposRepo.getRepos(user).observeOn(uiSchedulers).subscribe({ repos ->
            reposListPresenter.repos.clear()
            reposListPresenter.repos.addAll(repos)
            viewState.updateList()
        }, {
            println("Error: ${it.message}")
        }).addTo(compositeDisposable)
    }

    fun backPressed(): Boolean {
        router.backTo(Screens.UsersScreen())
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}