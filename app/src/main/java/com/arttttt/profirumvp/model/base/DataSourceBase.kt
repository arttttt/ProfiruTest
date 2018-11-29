package com.arttttt.profirumvp.model.base

import kotlinx.coroutines.*

abstract class DataSourceBase<in P, out T>(private val scope: CoroutineScope = GlobalScope,
                                 private val foregroundDispatcher: MainCoroutineDispatcher = Dispatchers.Main,
                                 private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    protected abstract suspend fun work(param: P?): Result

    fun get(onCompletion: (T) -> Unit, onError: (String) -> Unit, param: P? = null) {
        scope.launch(foregroundDispatcher) {
            try {
                val result = withContext(backgroundDispatcher) { work(param) }

                when (result) {
                    is Result.Success<*> -> onCompletion(result.result as T)
                    is Result.Error -> result.ex.message?.let { onError(it) }
                }
            }
            catch (ex: Exception) {
                ex.message?.let { onError(it) }
            }
        }
    }
}