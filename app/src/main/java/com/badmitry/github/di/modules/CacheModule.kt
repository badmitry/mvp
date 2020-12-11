package com.badmitry.github.di.modules

import androidx.room.Room
import com.badmitry.github.mvp.model.entity.cache.IRepoCache
import com.badmitry.github.mvp.model.entity.cache.IUserCache
import com.badmitry.github.mvp.model.entity.room.RoomGithubRepoCache
import com.badmitry.github.mvp.model.entity.room.RoomGithubUserCache
import com.badmitry.github.mvp.model.entity.room.database.Database
import com.badmitry.github.ui.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IUserCache = RoomGithubUserCache(database)

    @Singleton
    @Provides
    fun reposCache(database: Database): IRepoCache = RoomGithubRepoCache(database)
}