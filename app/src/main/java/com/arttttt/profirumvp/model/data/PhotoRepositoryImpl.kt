package com.arttttt.profirumvp.model.data

import android.graphics.Bitmap
import com.arttttt.profirumvp.model.data.base.Repository

class PhotoRepositoryImpl: Repository<Bitmap> {
    override fun loadAsync(callback: Repository.RepositoryLoadAsyncCallback<Bitmap>) {}
}