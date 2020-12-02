package com.badmitry.github.mvp.model.repo

import com.badmitry.github.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers() : Single<List<GithubUser>>
}
