package com.arttttt.profirumvp.model.photo.base

import com.arttttt.profirumvp.model.photo.Photo

interface PhotoRepository {
    fun getPhoto(onCompletion: (Photo) -> Unit, onError: (String) -> Unit, url: String)
}