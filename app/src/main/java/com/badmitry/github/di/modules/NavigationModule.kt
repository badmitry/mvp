package com.badmitry.github.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class NavigationModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun cicerone() = cicerone

    @Provides
    fun navigatorHolder() = cicerone.navigatorHolder

    @Provides
    fun router(): Router = cicerone.router
}