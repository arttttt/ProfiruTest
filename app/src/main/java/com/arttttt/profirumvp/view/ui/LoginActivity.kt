package com.arttttt.profirumvp.view.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.arttttt.profirumvp.R
import com.arttttt.profirumvp.presenter.login.LoginContract
import com.arttttt.profirumvp.presenter.login.LoginPreseter
import com.arttttt.profirumvp.utils.ActivityUtils
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    companion object {
        fun start(context: Context) {
            ActivityUtils.startActivity<LoginActivity>(context,
                null,
                null,
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
    }

    private val presenter: LoginContract.Presenter = LoginPreseter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener { presenter.onLoginButtonClicked() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object: VKCallback<VKAccessToken> {
                override fun onResult(res: VKAccessToken?) {
                    UsersActivity.start(this@LoginActivity)
                }

                override fun onError(error: VKError?) {
                    Snackbar.make(container, R.string.login_failed_text, Snackbar.LENGTH_LONG).show()
                }
            }))
        {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun startLoginProcess() {
        VKSdk.login(this, VKScope.FRIENDS, VKScope.PHOTOS)
    }
}
