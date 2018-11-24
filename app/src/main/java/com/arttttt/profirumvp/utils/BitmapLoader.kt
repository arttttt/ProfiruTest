package com.arttttt.profirumvp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.arttttt.profirumvp.model.cache.FileCache
import kotlinx.coroutines.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URL

class BitmapLoader(private val scope: CoroutineScope = GlobalScope,
                   private val mainDispatcher: MainCoroutineDispatcher = Dispatchers.Main,
                   private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val fileCache = FileCache()

    fun loadBitmap(url: String, onComplete: (Bitmap?) -> Unit, onError: (Throwable) -> Unit) {
        scope.launch(mainDispatcher) {
            try {
                val result = async(backgroundDispatcher) { getBitmap(url) }
                onComplete(result.await())
            } catch (e: CancellationException) {
                e.printStackTrace()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun getBitmap(url: String): Bitmap? {
        var file: File? = null
        if (fileCache.isFileInCache(url)) {
            file = fileCache.getFile(url)
        }

        var bitmap: Bitmap? = null
        if (file != null) {
            bitmap = BitmapFactory.decodeStream(FileInputStream(file))
        }

        if (bitmap == null) {
            val imageUrl = URL(url)
            var inputStream = imageUrl.openConnection().getInputStream()

            file = fileCache.getFile(url)
            val outputStream = FileOutputStream(file)
            StreamUtils.copyStream(inputStream, outputStream)
            outputStream.close()
            inputStream = FileInputStream(file)

            bitmap = BitmapFactory.decodeStream(inputStream)
        }

        return bitmap
    }
}