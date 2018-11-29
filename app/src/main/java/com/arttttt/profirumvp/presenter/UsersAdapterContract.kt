package com.arttttt.profirumvp.presenter

import com.arttttt.profirumvp.model.user.User

interface UsersAdapterContract {
    interface View {
        fun notifyAdapter()
        fun handleItemClick(position: Int, sharedViewId: Int)
    }

    interface ViewHolder {
        fun setFirstName(firstName: String)
        fun setLastName(lastName: String)
        fun setPhotoImage(url: String)
        fun setOnPhotoClickListener(clickListener: (position: Int, sharedViewId: Int) -> Unit)
    }

    interface Presenter {
        fun bind(position: Int, holder: ViewHolder)
        fun getItemAt(position: Int): User
        fun getUsersCount(): Int
        fun onItemClick(position: Int, sharedViewId: Int)
        fun putUsers(users: List<User>)
    }
}