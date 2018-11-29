package com.arttttt.profirumvp.presenter.login

interface LoginContract {
    interface View {
        fun startLoginProcess()
    }

    interface Presenter {
        fun onLoginButtonClicked()
    }
}