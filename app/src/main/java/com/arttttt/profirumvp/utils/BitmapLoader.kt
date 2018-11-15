package com.arttttt.profirumvp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.arttttt.profirumvp.base.DataLoaderBase
import com.arttttt.profirumvp.base.SingletonHolder
import com.arttttt.profirumvp.cache.FileCache
import com.arttttt.profirumvp.cache.MemoryCache
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.net.URL
import java.util.*

class BitmapLoader: DataLoaderBase<Bitmap>() {

    companion object: SingletonHolder<BitmapLoader>(::BitmapLoader)

    private val memoryCache = MemoryCache()
    private val fileCache = FileCache()

    private val tasks: MutableMap<String, Task> = Collections.synchronizedMap(WeakHashMap<String, Task>())

    override fun cancelLoading(url: String) {
        tasks[url]?.cancel(true)
        tasks.remove(url)
    }

    override fun loadData(url: String, callback: DataLoadedCallback<Bitmap>) {
        val bitmap = memoryCache.get(url)
        if (bitmap != null)
            callback.onDataLoaded(bitmap)
        else {
            with(Task(callback)) {
                tasks[url] = this
                execute(url)
            }
        }
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

    private inner class Task(private val callback: DataLoadedCallback<Bitmap>): AsyncTask<String, Void, Bitmap>() {
        override fun doInBackground(vararg params: String?): Bitmap? {
            val bitmap = getBitmap(params[0]!!)

            if (bitmap != null)
                memoryCache.put(params[0]!!, bitmap)

            tasks.remove(params[0]!!)

            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)

            if (!isCancelled && result != null)
                callback.onDataLoaded(result)
        }
    }
}