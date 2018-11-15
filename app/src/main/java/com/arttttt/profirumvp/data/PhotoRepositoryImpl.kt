package com.arttttt.profirumvp.data

import android.graphics.Bitmap
import com.arttttt.profirumvp.data.base.Repository

class PhotoRepositoryImpl: Repository<Bitmap> {
    override fun loadAsync(callback: Repository.RepositoryLoadAsyncCallback<Bitmap>) {}
}