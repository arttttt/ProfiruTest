package com.arttttt.profirumvp.base

abstract class DataLoaderBase<T> {
    abstract fun cancelLoading(url: String)
    abstract fun loadData(url: String, callback: DataLoadedCallback<T>)

    interface DataLoadedCallback<T> {
        fun onDataLoaded(data: T)
    }
}