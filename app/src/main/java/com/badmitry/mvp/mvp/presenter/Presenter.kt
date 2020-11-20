package com.badmitry.mvp.mvp.presenter

import com.badmitry.mvp.mvp.model.IModel
import com.badmitry.mvp.mvp.view.IView

class Presenter (private val model: IModel, private val IView: IView) {

    fun clickFirstBtn() {
        IView.setFirstBtnText(model.plusValue(0).toString())
    }

    fun clickSecondBtn() {
        IView.setSecondBtnText(model.plusValue(1).toString())
    }

    fun clickThirdBtn() {
        IView.setThirdBtnText(model.plusValue(2).toString())
    }
}