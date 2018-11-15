package com.arttttt.profirumvp.adapters

import android.graphics.Bitmap
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.base.DataLoaderBase
import com.arttttt.profirumvp.mvp.UsersViewHolderContract
import com.arttttt.profirumvp.mvp.UsersViewHolderPresenter
import com.arttttt.profirumvp.utils.BitmapLoader

class UsersViewHolder(view: View): RecyclerView.ViewHolder(view), UsersViewHolderContract.View {
    private val photo: ImageView
    private val firstName: TextView
    private val lastName: TextView
    private val photoLoadingProgressBar: ContentLoadingProgressBar

    val presenter = UsersViewHolderPresenter(this)

    init {
        photo = view.findViewById(R.id.photo)
        firstName = view.findViewById(R.id.first_name)
        lastName = view.findViewById(R.id.last_name)

        photoLoadingProgressBar = view.findViewById(R.id.photoLoading)

        photo.setOnClickListener {
            presenter.handleItemClick(it, adapterPosition)
        }
    }

    override fun cancelPhotoUpdate(url: String) {
        BitmapLoader.getInstance().cancelLoading(url)
    }

    override fun setFirstName(firstName: String) {
        this.firstName.text = firstName
    }

    override fun setLastName(lastName: String) {
        this.lastName.text = lastName
    }

    override fun setPhotoImage(url: String) {
        BitmapLoader.getInstance().loadData(url, object: DataLoaderBase.DataLoadedCallback<Bitmap> {
            override fun onDataLoaded(data: Bitmap) {
                photoLoadingProgressBar.hide()
                photo.setImageBitmap(data)
            }
        })
    }
}