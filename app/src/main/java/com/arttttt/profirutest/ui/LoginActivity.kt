package com.arttttt.profirutest.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.activity_login.*
import android.support.design.widget.Snackbar
import com.arttttt.profirutest.R
import com.vk.sdk.api.VKError
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            VKSdk.login(this, VKScope.FRIENDS, VKScope.PHOTOS)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object: VKCallback<VKAccessToken> {
                override fun onResult(res: VKAccessToken?) {
                    val intent = Intent(applicationContext, UsersActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                }

                override fun onError(error: VKError?) {
                    Snackbar.make(container, R.string.login_failed, Snackbar.LENGTH_LONG).show()
                }
            }))
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}