package com.arttttt.profirumvp.presenter.users

import com.arttttt.profirumvp.model.user.User

interface UsersContract {
    interface View {
        fun showErrorMessage(message: String)
        fun showLoadingIndicator(show: Boolean)
        fun showUsers(users: List<User>)
        fun startPhotoActivity(sharedViewId: Int, position: Int, url: String)
    }

    interface Presenter {
        fun getUsers()
        fun openUserPhoto(sharedViewId: Int, position:Int,  url: String)
    }
}