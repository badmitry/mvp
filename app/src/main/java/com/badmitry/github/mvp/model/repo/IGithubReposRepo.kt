package com.badmitry.github.mvp.model.repo

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import io.reactivex.rxjava3.core.Single

interface IGithubReposRepo {
    fun getRepos(user: GithubUser): Single<List<GithubUserRepo>>
}