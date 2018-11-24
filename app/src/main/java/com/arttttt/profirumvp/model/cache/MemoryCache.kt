package com.arttttt.profirumvp.model.cache

import android.graphics.Bitmap
import java.util.*

class MemoryCache {
    private val cache = Collections.synchronizedMap(
        LinkedHashMap<String, Bitmap>(10, 1.5f, true)
    )
    private var size: Long = 0
    private var limit: Long = 1000000

    init {
        setLimit(Runtime.getRuntime().maxMemory() / 4)
    }

    fun clear() {
        try {
            cache.clear()
            size = 0
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        }

    }

    fun get(id: String): Bitmap? {
        return try {
            if (!cache.containsKey(id))
                null
            else
                cache[id]
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
            null
        }
    }

    fun put(id: String, bitmap: Bitmap) {
        try {
            if (cache.containsKey(id))
                size -= getSizeInBytes(cache[id])
            cache[id] = bitmap
            size += getSizeInBytes(bitmap)
            checkSize()
        } catch (th: Throwable) {
            th.printStackTrace()
        }

    }

    private fun checkSize() {
        if (size > limit) {
            val iter = cache.entries.iterator()
            while (iter.hasNext()) {
                val entry = iter.next()
                size -= getSizeInBytes(entry.value)
                iter.remove()
                if (size <= limit)
                    break
            }
        }
    }


    private fun getSizeInBytes(bitmap: Bitmap?): Long {
        return if (bitmap == null)
            0
        else
            (bitmap.rowBytes * bitmap.height).toLong()
    }

    private fun setLimit(new_limit: Long) {
        limit = new_limit
    }
}