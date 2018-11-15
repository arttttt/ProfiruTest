package com.arttttt.profirumvp.ui

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.base.DataLoaderBase
import com.arttttt.profirumvp.utils.BitmapLoader
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PHOTO_URL_ID = "PHOTO_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val extras = intent.extras
        val url = extras.getString(EXTRA_PHOTO_URL_ID)

        BitmapLoader.getInstance().loadData(url, object: DataLoaderBase.DataLoadedCallback<Bitmap> {
            override fun onDataLoaded(data: Bitmap) {
                photoLoading.hide()
                photo.setImageBitmap(data)
            }
        })

        photo.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}
