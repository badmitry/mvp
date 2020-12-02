package com.badmitry.github.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.badmitry.github.R
import com.badmitry.github.mvp.model.image.IImageLoader
import com.badmitry.github.mvp.presenter.list.IUserListPresenter
import com.badmitry.github.mvp.view.list.IUserItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_main.view.*

class UserRVAdapter(private val presenter: IUserListPresenter, private val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))

    override fun getItemCount(): Int = presenter.getCount()
    override fun onBindViewHolder(holder: UserRVAdapter.ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener() {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    inner class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer, IUserItemView {
        override var pos = -1
        override fun setLogin(login: String) = with(containerView) {
            tv_login.text = login
        }

        override fun loadAvatar(url: String?) = with(containerView) {
            imageLoader.loadInto(url, iv_avatar)
        }
    }
}