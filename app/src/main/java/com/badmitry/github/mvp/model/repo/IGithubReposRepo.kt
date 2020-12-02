package com.badmitry.github.mvp.model.repo

import com.badmitry.github.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single

interface IGithubReposRepo {
    fun getRepos(url: String): Single<List<GithubUserRepo>>
}