package com.arttttt.profirumvp.presenter.login

class LoginPresenter(private val view: LoginContract.View): LoginContract.Presenter {
    override fun onLoginButtonClicked() {
        view.startLoginProcess()
    }
}