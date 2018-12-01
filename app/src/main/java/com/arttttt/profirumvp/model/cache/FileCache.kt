package com.arttttt.profirumvp.model.cache

import java.io.File

class FileCache {
    private var cacheDir: File

    init {
        cacheDir = File(android.os.Environment.getExternalStorageDirectory(), "images_cache")

        if (!cacheDir.exists())
            cacheDir.mkdirs()
    }

    fun isFileInCache(fileName: String): Boolean {
        val fileNameHash = fileName.hashCode().toString()

        return File(cacheDir, fileNameHash).exists()
    }

    fun getFile(fileName: String): File {
        val fileNameHash = fileName.hashCode().toString()

        return File(cacheDir, fileNameHash)

    }

    fun clear() {
        val files = cacheDir.listFiles() ?: return
        files.forEach {
            it.delete()
        }
    }

}