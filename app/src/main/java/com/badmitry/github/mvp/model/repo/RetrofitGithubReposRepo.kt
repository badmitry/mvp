package com.badmitry.github.mvp.model.repo

import com.badmitry.github.mvp.model.api.IDataSource
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubReposRepo(private val api: IDataSource): IGithubReposRepo {
    override fun getRepos(url: String): Single<List<GithubUserRepo>> =
        api.getUserRepo(url).subscribeOn(Schedulers.io())
}