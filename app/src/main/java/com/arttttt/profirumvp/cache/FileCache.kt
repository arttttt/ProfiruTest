package com.arttttt.profirumvp.cache

import android.content.Context
import java.io.File


class FileCache {

    private var cacheDir: File

    init {
        cacheDir = File(android.os.Environment.getExternalStorageDirectory(), "images_cache")

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