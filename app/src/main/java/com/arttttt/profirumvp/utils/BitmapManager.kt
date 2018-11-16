package com.arttttt.profirumvp.utils

import android.graphics.Bitmap
import android.support.v4.widget.ContentLoadingProgressBar
import android.widget.ImageView
import com.arttttt.profirumvp.base.SingletonHolder
import com.arttttt.profirumvp.cache.MemoryCache
import java.lang.ref.WeakReference

class BitmapManager private constructor() {
    companion object: SingletonHolder<BitmapManager>(::BitmapManager)

    private val bitmapLoader = BitmapLoader()
    private val memoryCache = MemoryCache()

    fun getBitmapFromUrl(view: ImageView, progressView: ContentLoadingProgressBar?, url: String) {
        val bitmap = memoryCache.get(url)
        if (bitmap != null) {
            progressView?.hide()
            view.setImageBitmap(bitmap)

            return
        }

        val imageViewRef = WeakReference<ImageView>(view)
        var progressViewRef: WeakReference<ContentLoadingProgressBar>? = null
        if (progressView != null)
            progressViewRef = WeakReference(progressView)

        bitmapLoader.loadData(url, object: BitmapLoader.BitmapLoadedCallback {
            override fun onDataLoaded(data: Bitmap) {
                memoryCache.put(url, data)

                progressViewRef?.get()?.hide()
                imageViewRef.get()?.setImageBitmap(data)
            }
        })
    }
}