package com.arttttt.profirumvp.model.cache.base

interface MemoryCache<T> {
    fun clear()
    fun get(key: String): T?
    fun initialize(): MemoryCache<T>
    fun put(key: String, value: T)
}