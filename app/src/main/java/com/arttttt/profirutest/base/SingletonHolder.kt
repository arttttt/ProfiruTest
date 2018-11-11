package com.arttttt.profirutest.base

open class SingletonHolder<out T>(private val creator: () -> T) {

    private var instance: T? = null

    fun getInstance(): T {
        if (instance == null)
            instance = creator()

        return instance!!
    }
}