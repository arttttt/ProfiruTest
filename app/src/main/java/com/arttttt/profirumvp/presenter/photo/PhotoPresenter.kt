package com.arttttt.profirumvp.presenter.photo

import com.arttttt.profirumvp.model.photo.base.PhotoRepository

open class PhotoPresenter(private val view: PhotoContract.View,
                     private val photoRepository: PhotoRepository): PhotoContract.Presenter {
    override fun loadPhoto(url: String) {
        photoRepository.getPhoto({
            view.setPhoto(it)
        }, {}, url)
    }

    override fun onPhotoClicked() {
        view.destroyView()
    }
}