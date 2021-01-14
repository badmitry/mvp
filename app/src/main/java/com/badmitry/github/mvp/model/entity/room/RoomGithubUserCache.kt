package com.badmitry.github.mvp.model.entity.room

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.cache.IUserCache
import com.badmitry.github.mvp.model.entity.room.database.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUserCache(private val db: Database): IUserCache {

    override fun loadInCache(users: List<GithubUser>) = Completable.fromAction {
        val roomUsers = users.map {user ->
            RoomGithubUsers(
                user.id,
                user.login,
                user.avatarUrl ?: "",
                user.reposUrl
            )
        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

    override fun takeFromCache() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.repoUrl)
        }
    }.subscribeOn(Schedulers.io())
}