package com.arttttt.profirumvp.base

import android.app.Application
import com.arttttt.profirumvp.di.AppModule
import com.vk.sdk.VKSdk
import org.koin.android.ext.android.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(AppModule.module))
        VKSdk.initialize(this)
    }
}