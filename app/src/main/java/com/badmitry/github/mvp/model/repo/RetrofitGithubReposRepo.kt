package com.badmitry.github.mvp.model.repo

import com.badmitry.github.mvp.model.api.IDataSource
import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import com.badmitry.github.mvp.model.entity.cache.IRepoCache
import com.badmitry.github.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubReposRepo(
    private val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cacheRepo: IRepoCache
) : IGithubReposRepo {
    override fun getRepos(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getUserRepo(url).flatMap { repositories ->
                        cacheRepo.loadInCache(user, repositories).toSingleDefault(repositories)
                    }
                } ?: Single.error<List<GithubUserRepo>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                cacheRepo.takeFromCache(user)
            }
        }.subscribeOn(Schedulers.io())
}