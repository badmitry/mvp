package com.badmitry.github.mvp.view.list

interface IUserItemView : IItemView {
    fun setLogin(login: String)
    fun loadAvatar(url: String?)
}