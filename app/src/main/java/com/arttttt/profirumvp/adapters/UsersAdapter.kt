package com.arttttt.profirumvp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.mvp.UsersAdapterContract
import com.arttttt.profirumvp.mvp.UsersAdapterPresenter

class UsersAdapter(private val photoClickListener: PhotoClickListener): RecyclerView.Adapter<UsersViewHolder>(), UsersAdapterContract.View {

    val presenter: UsersAdapterContract.Presenter = UsersAdapterPresenter(this)

    override fun handleItemClick(view: View, position: Int) {
        photoClickListener.onPhotoClick(view, presenter.getItemAt(position).photoUrl)
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.user_item, parent, false)

        return UsersViewHolder(view).apply {
            this.presenter.usersAdapterPresenter = this@UsersAdapter.presenter
        }
    }

    override fun getItemCount() = presenter.getUsersCount()

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.presenter.position = position
        holder.presenter.bind()
    }

    override fun onViewRecycled(holder: UsersViewHolder) {
        super.onViewRecycled(holder)

        holder.presenter.cancelPhotoUpdate(presenter.getItemAt(holder.adapterPosition).photoUrl)
    }

    interface PhotoClickListener {
        fun onPhotoClick(view: View, url: String)
    }
}