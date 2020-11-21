package com.badmitry.github.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.badmitry.github.R
import com.badmitry.github.databinding.MainLayoutBinding
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
    private lateinit var binding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_layout)
        binding = DataBindingUtil.setContentView(this, R.layout.main_layout)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)
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