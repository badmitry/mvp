package com.badmitry.github.mvp.presenter.list

import com.badmitry.github.mvp.view.list.IItemView

interface IListPresenter <V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}