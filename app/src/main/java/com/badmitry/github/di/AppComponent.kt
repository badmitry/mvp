package com.badmitry.github.di

import com.badmitry.github.di.modules.*
import com.badmitry.github.mvp.presenter.MainPresenter
import com.badmitry.github.mvp.presenter.RepoPresenter
import com.badmitry.github.mvp.presenter.UserPresenter
import com.badmitry.github.mvp.presenter.UsersPresenter
import com.badmitry.github.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        NavigationModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(repoPresenter: RepoPresenter)
}