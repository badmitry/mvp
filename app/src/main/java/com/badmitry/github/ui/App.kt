package com.badmitry.github.ui

import android.app.Application
import com.badmitry.github.di.AppComponent
import com.badmitry.github.di.DaggerAppComponent
import com.badmitry.github.di.modules.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
        val component get() = instance._appComponent
    }

    private lateinit var _appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        _appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}