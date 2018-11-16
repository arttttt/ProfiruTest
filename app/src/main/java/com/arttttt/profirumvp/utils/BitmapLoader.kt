package com.arttttt.profirumvp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.arttttt.profirumvp.base.DataLoaderBase
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URL
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.Future

class BitmapLoader: DataLoaderBase<Bitmap>(true) {

    override val executorService by lazy { Executors.newFixedThreadPool(5) }

    override fun loadData(url: String, onDataLoadedCallback: DataLoadedCallback<Bitmap>) {
        executorService.submit(BitmapTask(url, onDataLoadedCallback))
    }


    private inner class BitmapTask(private val url: String, private val callback: DataLoadedCallback<Bitmap>)
        : BaseTask() {
        override fun run() {

            var file: File? = null
            if (fileCache != null && fileCache.isFileInCache(url)) {
                file = fileCache.getFile(url)
            }

            var bitmap: Bitmap? = null
            if (file != null) {
                bitmap = BitmapFactory.decodeStream(FileInputStream(file))
            }

            if (bitmap == null) {
                val imageUrl = URL(url)
                var inputStream = imageUrl.openConnection().getInputStream()

                if (fileCache != null) {
                    file = fileCache.getFile(url)
                    val outputStream = FileOutputStream(file)
                    StreamUtils.copyStream(inputStream, outputStream)
                    outputStream.close()
                    inputStream = FileInputStream(file)
                }

                bitmap = BitmapFactory.decodeStream(inputStream)
            }

            if (bitmap != null)
                callback.onDataLoaded(bitmap)
        }
    }

    interface BitmapLoadedCallback: DataLoadedCallback<Bitmap>
}