package com.arttttt.profirumvp.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.model.photo.Photo
import com.arttttt.profirumvp.model.photo.PhotoDataSourceImpl
import com.arttttt.profirumvp.model.photo.PhotoRepositoryImpl
import com.arttttt.profirumvp.presenter.photo.PhotoContract
import com.arttttt.profirumvp.presenter.photo.PhotoPresenter
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoActivity : AppCompatActivity(), PhotoContract.View {

    companion object {
        const val EXTRA_PHOTO_URL_ID = "PHOTO_URL"
    }

    private val presenter: PhotoContract.Presenter = PhotoPresenter(this,
        PhotoRepositoryImpl.getInstance(PhotoDataSourceImpl()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val extras = intent.extras
        val url = extras?.getString(EXTRA_PHOTO_URL_ID)

        url?.let {
            presenter.loadPhoto(url)
        }

        userPhoto.setOnClickListener { presenter.onPhotoClicked() }
    }

    override fun destroyView() {
        supportFinishAfterTransition()
    }

    override fun setPhoto(photo: Photo) {
        photoLoadingIndicator.hide()
        userPhoto.setImageBitmap(photo.bitmap)
    }
}
