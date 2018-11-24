package com.arttttt.profirumvp.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.utils.BitmapManager
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

        BitmapManager.getInstance().getBitmapFromUrl(photo, photoLoading, url)

        photo.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}
