package com.badmitry.github.di.modules

import com.badmitry.github.ui.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@Module
class AppModule(val app: App) {

    @Provides
    fun app() = app

    @Provides
    fun uiSchelduler() = AndroidSchedulers.mainThread()

}