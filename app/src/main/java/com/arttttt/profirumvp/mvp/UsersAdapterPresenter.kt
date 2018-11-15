package com.arttttt.profirumvp.mvp

import com.arttttt.profirumvp.model.User

class UsersAdapterPresenter(private val view: UsersAdapterContract.View): UsersAdapterContract.Presenter {

    private val users = mutableListOf<User>()

    override fun getUsersCount() = users.size
    override fun getItemAt(position: Int) = users[position]
    override fun putUsers(users: List<User>) {
        this.users.addAll(users)
        view.notifyAdapter()
    }

    override fun handleItemClick(clickedView: android.view.View, position: Int) {
        view.handleItemClick(clickedView, position)
    }
}