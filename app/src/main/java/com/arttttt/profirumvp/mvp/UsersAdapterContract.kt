package com.arttttt.profirumvp.mvp

import com.arttttt.profirumvp.model.User

interface UsersAdapterContract {
    interface View {
        fun notifyAdapter()
        fun handleItemClick(view: android.view.View, position: Int)
    }

    interface Presenter {
        fun getItemAt(position: Int): User
        fun getUsersCount(): Int
        fun handleItemClick(clickedView: android.view.View, position: Int)
        fun putUsers(users: List<User>)
    }
}