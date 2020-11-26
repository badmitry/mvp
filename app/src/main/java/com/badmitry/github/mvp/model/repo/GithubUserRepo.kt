package com.badmitry.github.mvp.model.repo

import android.util.Log
import com.badmitry.github.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class GithubUsersRepo {
    private var repositories = mutableListOf<GithubUser>()
    private var newRepo = mutableListOf<GithubUser>()
    var counter = 0

    private fun downloadUsers(): MutableList<GithubUser> {
        return mutableListOf(
            GithubUser("login1"),
            GithubUser("login2"),
            GithubUser("login3"),
            GithubUser("login4"),
            GithubUser("login5")
        )
    }

    fun create() = Observable.create<MutableList<GithubUser>> { emitter ->
        while (true) {
            counter++
            newRepo = downloadUsers()
            if (counter == 5) {
                newRepo.add(GithubUser("login6"))
            }
            if (!newRepo.equals(repositories)) {
                repositories = newRepo
                Log.d("!!!", "downloadUsers: " + false)
                emitter.onNext(repositories)
                if (counter == 5) {
                    emitter.onComplete()
                    break
                }
            } else {
                Log.d("!!!", "downloadUsers: " + true)
            }
            try {
                TimeUnit.SECONDS.sleep(1)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}
