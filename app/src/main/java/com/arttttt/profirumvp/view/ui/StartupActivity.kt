package com.arttttt.profirumvp.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vk.sdk.VKSdk

class StartupActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (VKSdk.wakeUpSession(this))
            UsersActivity.start(this)
        else
            LoginActivity.start(this)

        finish()
    }
}
