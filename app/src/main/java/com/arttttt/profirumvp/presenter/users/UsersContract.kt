package com.arttttt.profirumvp.presenter.users

import com.arttttt.profirumvp.model.user.User
import com.arttttt.profirumvp.model.user.base.UsersRepository

interface UsersContract {
    interface View {
        fun showErrorMessage(message: String)
        fun showLoadingIndicator(show: Boolean)
        fun showUsers(users: List<User>)
        fun startPhotoActivity(view: android.view.View, url: String)
    }

    interface Presenter {
        fun getUsers(usersRepository: UsersRepository)
        fun openUserPhoto(viewToAnimate: android.view.View, url: String)
    }
}