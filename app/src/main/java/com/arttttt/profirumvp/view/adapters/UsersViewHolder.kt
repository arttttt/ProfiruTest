package com.arttttt.profirumvp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.arttttt.profirumvp.model.photo.Photo
import com.arttttt.profirumvp.presenter.usersadapter.UsersAdapterContract
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user_item.*

class UsersViewHolder(override val containerView: View,
                      itemClick: (position: Int, sharedViewId: Int) -> Unit): RecyclerView.ViewHolder(containerView),
    UsersAdapterContract.ViewHolder,
    LayoutContainer {

    init {
        userPhoto.setOnClickListener { view ->
            itemClick(adapterPosition, view.id)
        }
    }

    override fun setFirstName(firstName: String) {
        first_name.text = firstName
    }

    override fun setLastName(lastName: String) {
        last_name.text = lastName
    }

    override fun setPhotoImage(photo: Photo) {
        userPhoto.setImageBitmap(photo.bitmap)
    }

    override fun showLoadingIndicator(show: Boolean) = if (show)
        photoLoadingIndicator.show()
    else
        photoLoadingIndicator.hide()
}