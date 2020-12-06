package com.badmitry.github.mvp.model.entity.room

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.cache.IUserCache
import com.badmitry.github.mvp.model.entity.room.database.Database

class RoomGithubUserCache(private val db: Database): IUserCache {

    val userList: List<GithubUser> = mutableListOf()
    override fun loadInCache(users: List<GithubUser>) {
        val roomUsers = users.map {user ->
            RoomGithubUsers(
                user.id,
                user.login,
                user.avatarUrl ?: "",
                user.reposUrl
            )
        }
        db.userDao.insert(roomUsers)
    }

    override fun takeFromCache(): List<RoomGithubUsers> {
        return db.userDao.getAll()
    }
}