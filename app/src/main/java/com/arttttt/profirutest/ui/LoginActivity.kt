package com.arttttt.profirutest.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.activity_login.*
import android.support.design.widget.Snackbar
import com.arttttt.profirutest.R
import com.arttttt.profirutest.utils.ActivityHelper
import com.vk.sdk.api.VKError
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            VKSdk.wakeUpSession(this, object : VKCallback<VKSdk.LoginState> {
                override fun onResult(res: VKSdk.LoginState?) {
                    when (res) {
                        VKSdk.LoginState.LoggedIn -> startUsersActivity()
                        VKSdk.LoginState.LoggedOut -> VKSdk.login(this@LoginActivity, VKScope.FRIENDS, VKScope.PHOTOS)
                    }
                }

                override fun onError(error: VKError?) { }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object: VKCallback<VKAccessToken> {
                override fun onResult(res: VKAccessToken?) {
                    startUsersActivity()
                }

                override fun onError(error: VKError?) {
                    Snackbar.make(container, R.string.login_failed, Snackbar.LENGTH_LONG).show()
                }
            }))
        {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun startUsersActivity() {
        ActivityHelper.startActivity<UsersActivity>(applicationContext,
            null,
            null,
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        finish()
    }
}