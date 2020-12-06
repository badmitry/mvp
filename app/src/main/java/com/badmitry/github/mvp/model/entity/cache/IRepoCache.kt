package com.badmitry.github.mvp.model.entity.cache

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import com.badmitry.github.mvp.model.entity.room.RoomGithubRepos

interface IRepoCache {
    fun loadInCache(user: GithubUser, repos: List<GithubUserRepo>)
    fun takeFromCache(user: GithubUser): List<RoomGithubRepos>
}