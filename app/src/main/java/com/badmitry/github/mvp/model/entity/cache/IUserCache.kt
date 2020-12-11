package com.badmitry.github.mvp.model.entity.cache

import com.badmitry.github.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUserCache {
    fun loadInCache(users: List<GithubUser>): Completable
    fun takeFromCache(): Single<List<GithubUser>>
}