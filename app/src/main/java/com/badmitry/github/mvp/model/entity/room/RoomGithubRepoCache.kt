package com.badmitry.github.mvp.model.entity.room

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import com.badmitry.github.mvp.model.entity.cache.IRepoCache
import com.badmitry.github.mvp.model.entity.room.database.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RoomGithubRepoCache(private val db: Database): IRepoCache {
    override fun loadInCache(user: GithubUser, repos: List<GithubUserRepo>) =
        Completable.fromAction() {
        val roomUser = db.userDao.findByLogin(user.login)
            ?: throw RuntimeException("No such users in database")
        val roomRepo = repos.map {
            RoomGithubRepos(
                it.id,
                it.name,
                it.fork,
                roomUser.id
            )
        }
        db.repoDao.insert(roomRepo)
    }.subscribeOn(Schedulers.io())

    override fun takeFromCache(user: GithubUser): Single<List<GithubUserRepo>> = Single.fromCallable{
        val roomUser = db.userDao.findByLogin(user.login)
            ?: throw RuntimeException("No such users in database")
        return@fromCallable db.repoDao.findForUser(roomUser.id).map{
            GithubUserRepo(it.id, it.name, it.fork)
        }
    }.subscribeOn(Schedulers.io())
}