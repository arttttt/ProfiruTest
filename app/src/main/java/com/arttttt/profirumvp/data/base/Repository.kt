package com.arttttt.profirumvp.data.base

interface Repository<T> {
    fun loadAsync(callback: RepositoryLoadAsyncCallback<T>)

    interface RepositoryLoadAsyncCallback<T> {
        fun onDataLoaded(data: T)
        fun onError(message: String)
    }
}