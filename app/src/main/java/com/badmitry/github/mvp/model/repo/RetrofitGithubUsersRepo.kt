package com.badmitry.github.mvp.model.repo

import com.badmitry.github.mvp.model.api.IDataSource
import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.cache.IUserCache
import com.badmitry.github.mvp.model.entity.room.RoomGithubUsers
import com.badmitry.github.mvp.model.entity.room.database.Database
import com.badmitry.github.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IUserCache): IGithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = networkStatus.inOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    cache.loadInCache(users)
                    users
                }
            }
        } else {
            Single.fromCallable {
                cache.takeFromCache().map {roomUser ->
                    GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.repoUrl)
                }
            }
        }.subscribeOn(Schedulers.io())
    }
}