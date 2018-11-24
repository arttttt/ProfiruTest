package com.arttttt.profirumvp.presenter

import com.arttttt.profirumvp.model.User

class UsersAdapterPresenter(private val view: UsersAdapterContract.View):
    UsersAdapterContract.Presenter {

    private val users = mutableListOf<User>()

    override fun getUsersCount() = users.size
    override fun getItemAt(position: Int) = users[position]
    override fun putUsers(users: List<User>) {
        this.users.addAll(users)
        view.notifyAdapter()
    }

    override fun onItemClick(position: Int, sharedViewId: Int) {
        view.handleItemClick(position, sharedViewId)
    }
}