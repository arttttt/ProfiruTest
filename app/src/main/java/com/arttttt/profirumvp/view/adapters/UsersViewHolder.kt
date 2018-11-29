package com.arttttt.profirumvp.view.adapters

import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.presenter.UsersAdapterContract
import com.arttttt.profirumvp.utils.BitmapManager

class UsersViewHolder(view: View): RecyclerView.ViewHolder(view), UsersAdapterContract.ViewHolder {
    private val photo: ImageView
    private val firstName: TextView
    private val lastName: TextView
    private val photoLoadingProgressBar: ContentLoadingProgressBar

    init {
        photo = view.findViewById(R.id.photo)
        firstName = view.findViewById(R.id.first_name)
        lastName = view.findViewById(R.id.last_name)

        photoLoadingProgressBar = view.findViewById(R.id.photoLoading)
    }

    override fun setFirstName(firstName: String) {
        this.firstName.text = firstName
    }

    override fun setLastName(lastName: String) {
        this.lastName.text = lastName
    }

    override fun setOnPhotoClickListener(clickListener: (position: Int, sharedViewId: Int) -> Unit) {
        photo.setOnClickListener {
            clickListener(adapterPosition, R.id.photo)
        }
    }

    override fun setPhotoImage(url: String) {
        BitmapManager.getInstance().getBitmapFromUrl(photo, photoLoadingProgressBar, url)
    }
}