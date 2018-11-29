package com.arttttt.profirumvp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.model.photo.PhotoDataSourceImpl
import com.arttttt.profirumvp.model.photo.PhotoRepositoryImpl
import com.arttttt.profirumvp.model.photo.base.PhotoDataSource
import com.arttttt.profirumvp.presenter.usersadapter.UsersAdapterContract
import com.arttttt.profirumvp.presenter.usersadapter.UsersAdapterPresenter

class UsersAdapter(private val photoClickListener: PhotoClickListener): RecyclerView.Adapter<UsersViewHolder>(), UsersAdapterContract.View {

    val presenter: UsersAdapterContract.Presenter =
        UsersAdapterPresenter(this, PhotoRepositoryImpl.getInstance(PhotoDataSourceImpl()))

    override fun handleItemClick(position: Int, sharedViewId: Int) {
        val user = presenter.getItemAt(position)

        photoClickListener.onPhotoClick(user.photoUrl, sharedViewId, position)
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.user_item, parent, false)

        return UsersViewHolder(view).apply {
            setOnPhotoClickListener { position, sharedViewId -> handleItemClick(position, sharedViewId) }
        }
    }

    override fun getItemCount() = presenter.getUsersCount()

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) = presenter.bind(position, holder)

    interface PhotoClickListener {
        fun onPhotoClick(url: String, sharedViewId: Int, position: Int)
    }
}