package com.arttttt.profirutest.utils

import android.content.Context
import android.widget.ImageView
import com.arttttt.profirutest.cache.FileCache
import com.arttttt.profirutest.cache.MemoryCache
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.lang.ref.WeakReference
import java.net.URL

class ImageLoader(context: Context) {
    val memoryCache: MemoryCache
    val fileCache: FileCache
    var executorService: ExecutorService

    private val imageViews = Collections.synchronizedMap(WeakHashMap<ImageView, String>())

    init {
        memoryCache = MemoryCache()
        fileCache = FileCache(context)
        executorService = Executors.newFixedThreadPool(5)
    }

    fun displayImage(url: String, imageView: ImageView) {
        imageViews[imageView] = url
        val bitmap = memoryCache.get(url)
        if (bitmap != null)
            imageView.setImageBitmap(bitmap)
        else
            queuePhoto(url, imageView)
        }

    private fun queuePhoto(url: String, imageView: ImageView) {
        val photoToLoad = PhotoToLoad(url, WeakReference(imageView))
        executorService.submit(PhotosLoader(photoToLoad))
    }

    private fun getBitmap(url: String): Bitmap? {
        val file = fileCache.getFile(url)

        val bitmap = decodeFile(file)
        if (bitmap != null)
            return bitmap

        return try {
            val imageUrl = URL(url)
            val conn = imageUrl.openConnection()
            val inputStream = conn.getInputStream()
            val outputStream = FileOutputStream(file)
            StreamUtils.copyStream(inputStream, outputStream)
            outputStream.close()
            decodeFile(file)
        } catch (ex: Throwable) {
            ex.printStackTrace()
            if (ex is OutOfMemoryError)
                memoryCache.clear()
            null
        }

    }

    private fun decodeFile(file: File): Bitmap? {
        return try {
             BitmapFactory.decodeStream(FileInputStream(file))
        } catch (e: FileNotFoundException) {
            null
        }
    }

    inner class PhotoToLoad(var url: String, var imageView: WeakReference<ImageView>)

    inner class PhotosLoader(private var photoToLoad: PhotoToLoad): Runnable {
        override fun run() {
            val bitmap = getBitmap(photoToLoad.url)
            if (bitmap != null)
                memoryCache.put(photoToLoad.url, bitmap)

            photoToLoad.imageView.get()?.setImageBitmap(bitmap)
        }
    }

    fun clearCache() {
        memoryCache.clear()
        fileCache.clear()
    }
}