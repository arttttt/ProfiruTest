package com.arttttt.profirumvp.view.adapters

import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.model.photo.Photo
import com.arttttt.profirumvp.presenter.usersadapter.UsersAdapterContract

class UsersViewHolder(view: View): RecyclerView.ViewHolder(view), UsersAdapterContract.ViewHolder {
    private val userPhoto: ImageView
    private val firstName: TextView
    private val lastName: TextView
    private val photoLoadingProgressBar: ContentLoadingProgressBar

    init {
        userPhoto = view.findViewById(R.id.userPhoto)
        firstName = view.findViewById(R.id.first_name)
        lastName = view.findViewById(R.id.last_name)

        photoLoadingProgressBar = view.findViewById(R.id.photoLoadingIndicator)
    }

    override fun setFirstName(firstName: String) {
        this.firstName.text = firstName
    }

    override fun setLastName(lastName: String) {
        this.lastName.text = lastName
    }

    override fun setOnPhotoClickListener(clickListener: (position: Int, sharedViewId: Int) -> Unit) {
        userPhoto.setOnClickListener {
            clickListener(adapterPosition, it.id)
        }
    }

    override fun setPhotoImage(photo: Photo) {
        userPhoto.setImageBitmap(photo.bitmap)
    }

    override fun showLoadingIndicator(show: Boolean) = if (show)
        photoLoadingProgressBar.show()
    else
        photoLoadingProgressBar.hide()
}