package com.arttttt.profirumvp.model.photo

import com.arttttt.profirumvp.base.SingletonHolderWithParam
import com.arttttt.profirumvp.model.cache.PhotoCache
import com.arttttt.profirumvp.model.photo.base.PhotoDataSource
import com.arttttt.profirumvp.model.photo.base.PhotoRepository

class PhotoRepositoryImpl private constructor(private val dataSource: PhotoDataSource): PhotoRepository {

    companion object: SingletonHolderWithParam<PhotoDataSource, PhotoRepositoryImpl>(::PhotoRepositoryImpl)

    private val memoryCache = PhotoCache().initialize()

    override fun getPhoto(onCompletion: (Photo) -> Unit, onError: (String) -> Unit, url: String) {
        val photo = memoryCache.get(url)

        if (photo != null) {
            onCompletion(photo)
            return
        }

        dataSource.get({
            memoryCache.put(url, it)
            onCompletion(it)
        }, {
            onError(it)
        }, url)
    }
}