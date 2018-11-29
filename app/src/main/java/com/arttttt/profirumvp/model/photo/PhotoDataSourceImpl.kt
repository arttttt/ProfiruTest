package com.arttttt.profirumvp.model.photo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.arttttt.profirumvp.model.base.Result
import com.arttttt.profirumvp.model.cache.FileCache
import com.arttttt.profirumvp.model.photo.base.PhotoDataSource
import com.arttttt.profirumvp.utils.StreamUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class PhotoDataSourceImpl: PhotoDataSource() {
    private val fileCache = FileCache()

    override suspend fun work(param: String?): Result {
        if (param != null) {
            val bitmap = getBitmap(param)

            if (bitmap != null)
                return Result.Success(Photo(bitmap))
        }

        return Result.Error(NullPointerException())
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
            val urlConnection = imageUrl.openConnection() as HttpURLConnection

            var inputStream = urlConnection.inputStream
            file = fileCache.getFile(url)
            val outputStream = FileOutputStream(file)
            StreamUtils.copyStream(inputStream, outputStream)
            urlConnection.disconnect()
            outputStream.close()
            inputStream = FileInputStream(file)

            bitmap = BitmapFactory.decodeStream(inputStream)
        }

        return bitmap
    }
}