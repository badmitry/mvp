package com.badmitry.github.mvp.model.repo

import com.badmitry.github.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class GithubUsersRepo {
    private var repositories = mutableListOf<GithubUser>()
    private var newRepo = mutableListOf<GithubUser>()

    private fun downloadUsers(): MutableList<GithubUser> {
        return mutableListOf(
            GithubUser("login1"),
            GithubUser("login2"),
            GithubUser("login3"),
            GithubUser("login4"),
            GithubUser("login5")
        )
    }

    fun create() = Single.create<MutableList<GithubUser>> { emitter ->
        newRepo = downloadUsers()
        try {
            TimeUnit.SECONDS.sleep(1)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        if (!newRepo.equals(repositories)) {
            repositories = newRepo
        }
        emitter.onSuccess(repositories)
    }.subscribeOn(Schedulers.io())
}
