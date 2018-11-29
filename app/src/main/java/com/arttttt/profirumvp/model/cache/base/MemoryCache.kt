package com.arttttt.profirumvp.model.cache.base

import com.arttttt.profirumvp.model.photo.Photo

interface MemoryCache<T> {
    fun clear()
    fun get(key: String): Photo?
    fun initialize(): MemoryCache<T>
    fun put(key: String, value: T)
}