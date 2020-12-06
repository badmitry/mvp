package com.badmitry.github.mvp.model.entity.cache

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.room.RoomGithubUsers

interface IUserCache {
    fun loadInCache(users: List<GithubUser>)
    fun takeFromCache() : List<RoomGithubUsers>
}