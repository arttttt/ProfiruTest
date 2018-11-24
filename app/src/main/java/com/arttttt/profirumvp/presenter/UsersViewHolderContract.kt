package com.arttttt.profirumvp.presenter

interface UsersViewHolderContract {
    interface View {
        fun setFirstName(firstName: String)
        fun setLastName(lastName: String)
        fun setPhotoImage(url: String)
    }
    interface Presenter {
        fun bind()
        fun onItemClick(position: Int, sharedViewId: Int)
    }
}