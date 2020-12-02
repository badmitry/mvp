package com.badmitry.github.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badmitry.github.R
import com.badmitry.github.mvp.presenter.list.IReposListPresenter
import com.badmitry.github.mvp.view.list.IReposItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repositories.*

class ReposRVAdapter(private val presenter: IReposListPresenter) :
    RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repositories, parent, false)
        )

    override fun getItemCount() = presenter.getCount()
    override fun onBindViewHolder(holder: ReposRVAdapter.ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener() {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer, IReposItemView {
        override var pos = -1
        override fun setRepoName(repoName: String) {
            tv_repo.text = repoName
        }

    }
}