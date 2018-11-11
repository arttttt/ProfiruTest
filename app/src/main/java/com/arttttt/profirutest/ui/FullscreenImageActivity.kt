package com.arttttt.profirutest.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.arttttt.profirutest.R
import com.arttttt.profirutest.models.User
import com.arttttt.profirutest.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_fullscreen_image.*

class FullscreenImageActivity : AppCompatActivity() {

    private val imageLoader: ImageLoader = ImageLoader(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_image)

        val extras = intent.extras
        val url = extras.getParcelable<User>("user")

        imageLoader.displayImage(url.avatarUrl, image)

        image.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}
