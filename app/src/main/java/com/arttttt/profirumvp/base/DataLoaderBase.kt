package com.arttttt.profirumvp.base

import com.arttttt.profirumvp.cache.FileCache
import java.util.concurrent.Executor

abstract class DataLoaderBase<T>(useCache: Boolean) {

    protected abstract val executorService: Executor

    protected val fileCache: FileCache? = if (useCache) FileCache() else null

    abstract fun loadData(url: String, onDataLoadedCallback: DataLoadedCallback<T>)

    interface DataLoadedCallback<T> {
        fun onDataLoaded(data: T)
    }

    protected abstract inner class BaseTask: Runnable
}