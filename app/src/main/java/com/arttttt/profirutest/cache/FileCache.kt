package com.arttttt.profirutest.cache

import android.content.Context
import java.io.File


class FileCache(context: Context) {

    private var cacheDir: File

    init {
        cacheDir = if (android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED)
            File(android.os.Environment.getExternalStorageDirectory(), "images_cache")
        else
            context.cacheDir

        if (!cacheDir.exists())
            cacheDir.mkdirs()
    }

    fun getFile(url: String): File {
        val filename = url.hashCode().toString()

        return File(cacheDir, filename)

    }

    fun clear() {
        val files = cacheDir.listFiles() ?: return
        files.forEach {
            it.delete()
        }
    }

}