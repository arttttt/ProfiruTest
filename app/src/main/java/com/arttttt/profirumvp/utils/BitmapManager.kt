package com.arttttt.profirumvp.utils

import android.support.v4.widget.ContentLoadingProgressBar
import android.widget.ImageView
import com.arttttt.profirumvp.base.SingletonHolder
import com.arttttt.profirumvp.model.cache.MemoryCache
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

        bitmapLoader.loadBitmap(url, {
            if (it == null)
                return@loadBitmap

            memoryCache.put(url, it)

            progressViewRef?.get()?.hide()
            imageViewRef.get()?.setImageBitmap(it)
        }, { it.printStackTrace() })
    }
}