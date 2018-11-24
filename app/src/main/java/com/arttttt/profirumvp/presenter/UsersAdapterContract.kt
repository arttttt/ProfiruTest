package com.arttttt.profirumvp.presenter

import com.arttttt.profirumvp.model.User

interface UsersAdapterContract {
    interface View {
        fun notifyAdapter()
        fun handleItemClick(position: Int, sharedViewId: Int)
    }

    interface Presenter {
        fun getItemAt(position: Int): User
        fun getUsersCount(): Int
        fun onItemClick(position: Int, sharedViewId: Int)
        fun putUsers(users: List<User>)
    }
}