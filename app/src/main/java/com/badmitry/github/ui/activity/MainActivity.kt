package com.badmitry.github.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.badmitry.github.R
import com.badmitry.github.databinding.ActivityMainBinding
import com.badmitry.github.mvp.presenter.MainPresenter
import com.badmitry.github.mvp.view.MainView
import com.badmitry.github.ui.App
import com.badmitry.github.ui.BackBtnListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {
    private val navigatorHolder  = App.instance.navigatorHolder
    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }
//    private lateinit var binding: ActivityMainBinding
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackBtnListener && it.backPressed()) {
                return
            }
        }
        presenter.backClick()
    }
}