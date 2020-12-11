package com.badmitry.github.mvp.model.entity.cache

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRepoCache {
    fun loadInCache(user: GithubUser, repos: List<GithubUserRepo>): Completable
    fun takeFromCache(user: GithubUser): Single<List<GithubUserRepo>>
}