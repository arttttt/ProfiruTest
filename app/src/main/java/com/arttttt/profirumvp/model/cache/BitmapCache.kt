package com.arttttt.profirumvp.model.cache

import android.support.v4.util.LruCache
import com.arttttt.profirumvp.model.cache.base.MemoryCache
import com.arttttt.profirumvp.model.photo.Photo

class BitmapCache: MemoryCache<Photo> {
    private lateinit var lruCache: LruCache<String, Photo>

    override fun clear() {
        lruCache.evictAll()
    }

    override fun get(key: String): Photo? = lruCache.get(key)

    override fun initialize(): MemoryCache<Photo> {
        val maxCacheSize = Runtime.getRuntime().maxMemory() / 1024 / 8

        lruCache = LruCache(maxCacheSize.toInt())

        return this
    }

    override fun put(key: String, value: Photo) {
        lruCache.put(key, value)
    }
}