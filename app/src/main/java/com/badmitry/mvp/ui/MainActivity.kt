package com.badmitry.mvp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.badmitry.mvp.R
import com.badmitry.mvp.databinding.ActivityMainBinding
import com.badmitry.mvp.mvp.model.Model
import com.badmitry.mvp.mvp.presenter.Presenter
import com.badmitry.mvp.mvp.view.IView

class MainActivity : AppCompatActivity(), IView {

    private val presenter = Presenter(Model(), this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnCounter1.setOnClickListener { presenter.clickFirstBtn() }
        binding.btnCounter2.setOnClickListener { presenter.clickSecondBtn() }
        binding.btnCounter3.setOnClickListener { presenter.clickThirdBtn() }
    }

    override fun setFirstBtnText(int: String) {
        binding.btnCounter1.text = int
    }

    override fun setSecondBtnText(int: String) {
        binding.btnCounter2.text = int
    }

    override fun setThirdBtnText(int: String) {
        binding.btnCounter3.text = int
    }
}