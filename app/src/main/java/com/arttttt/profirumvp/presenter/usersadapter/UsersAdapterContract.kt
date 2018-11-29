package com.arttttt.profirumvp.presenter.usersadapter

import com.arttttt.profirumvp.model.photo.Photo
import com.arttttt.profirumvp.model.user.User

interface UsersAdapterContract {
    interface View {
        fun notifyAdapter()
        fun handleItemClick(position: Int, sharedViewId: Int)
    }

    interface ViewHolder {
        fun setFirstName(firstName: String)
        fun setLastName(lastName: String)
        fun setOnPhotoClickListener(clickListener: (position: Int, sharedViewId: Int) -> Unit)
        fun setPhotoImage(photo: Photo)
        fun showLoadingIndicator(show: Boolean)
    }

    interface Presenter {
        fun bind(position: Int, holder: ViewHolder)
        fun getItemAt(position: Int): User
        fun getUsersCount(): Int
        fun onItemClick(position: Int, sharedViewId: Int)
        fun putUsers(users: List<User>)
    }
}