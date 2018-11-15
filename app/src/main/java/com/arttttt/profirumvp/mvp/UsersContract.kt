package com.arttttt.profirumvp.mvp

import com.arttttt.profirumvp.model.User

interface UsersContract {
    interface View {
        fun showErrorMessage(message: String)
        fun showLoadingProgressbar(hide: Boolean)
        fun showUsers(users: List<User>)
        fun startPhotoActivity(view: android.view.View, url: String)
    }

    interface Presenter {
        fun getUsers()
        fun openUserPhoto(viewToAnimate: android.view.View, url: String)
    }
}