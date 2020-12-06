package com.badmitry.github.mvp.model.entity.room

import com.badmitry.github.mvp.model.entity.GithubUser
import com.badmitry.github.mvp.model.entity.GithubUserRepo
import com.badmitry.github.mvp.model.entity.cache.IRepoCache
import com.badmitry.github.mvp.model.entity.room.database.Database
import java.lang.RuntimeException

class RoomGithubRepoCache(private val db: Database): IRepoCache {
    override fun loadInCache(user: GithubUser, repos: List<GithubUserRepo>) {
        val roomUser = db.userDao.findByLogin(user.login)
            ?: throw RuntimeException("No such users in database")
        val roomRepo = repos.map {
            RoomGithubRepos(
                it.id,
                it.name,
                it.fork,
                roomUser.id
            )
        }
        db.repoDao.insert(roomRepo)
    }

    override fun takeFromCache(user: GithubUser): List<RoomGithubRepos> {
        val roomUser = db.userDao.findByLogin(user.login)
            ?: throw RuntimeException("No such users in database")
        return db.repoDao.findForUser(roomUser.id)
    }
}