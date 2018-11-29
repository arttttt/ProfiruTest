package com.arttttt.profirumvp.presenter.photo

import com.arttttt.profirumvp.model.photo.Photo
import com.arttttt.profirumvp.model.photo.base.PhotoRepository

interface PhotoContract {
    interface View {
        fun destroyView()
        fun setPhoto(photo: Photo)
    }

    interface Presenter {
        fun loadPhoto(url: String)
        fun onPhotoClicked()
    }
}