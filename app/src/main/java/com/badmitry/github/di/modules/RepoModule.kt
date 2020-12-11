package com.badmitry.github.di.modules

import com.badmitry.github.mvp.model.api.IDataSource
import com.badmitry.github.mvp.model.entity.cache.IRepoCache
import com.badmitry.github.mvp.model.entity.cache.IUserCache
import com.badmitry.github.mvp.model.network.INetworkStatus
import com.badmitry.github.mvp.model.repo.IGithubReposRepo
import com.badmitry.github.mvp.model.repo.IGithubUsersRepo
import com.badmitry.github.mvp.model.repo.RetrofitGithubReposRepo
import com.badmitry.github.mvp.model.repo.RetrofitGithubUsersRepo
import com.badmitry.github.ui.App
import com.badmitry.github.ui.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        cache: IUserCache,
        networkStatus: INetworkStatus
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun reposRepo(
        api: IDataSource,
        cache: IRepoCache,
        networkStatus: INetworkStatus
    ): IGithubReposRepo = RetrofitGithubReposRepo(api, networkStatus, cache)
}