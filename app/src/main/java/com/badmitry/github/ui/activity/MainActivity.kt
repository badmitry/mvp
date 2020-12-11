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
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val presenter by moxyPresenter { MainPresenter().apply {
        App.component.inject(this)
    } }
    private var binding: MainLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_layout)
        App.component.inject(this)
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}