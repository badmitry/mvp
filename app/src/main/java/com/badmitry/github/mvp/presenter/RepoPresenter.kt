package com.badmitry.github.mvp.presenter

import com.badmitry.github.mvp.model.entity.GithubUserRepo
import com.badmitry.github.mvp.view.IRepoView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepoPresenter(private val repo: GithubUserRepo, private val router: Router) :
    MvpPresenter<IRepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setRepoName(repo.name)
        if (repo.fork == "false") {
            viewState.setRepoIsFork("No forks found")
        } else {
            viewState.setRepoIsFork("Forks found")
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}