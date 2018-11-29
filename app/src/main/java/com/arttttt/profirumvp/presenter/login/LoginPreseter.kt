package com.arttttt.profirumvp.presenter.login

class LoginPreseter(private val view: LoginContract.View): LoginContract.Presenter {
    override fun onLoginButtonClicked() {
        view.startLoginProcess()
    }


}