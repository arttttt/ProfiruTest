package com.arttttt.profirumvp.presenter.users

import com.arttttt.profirumvp.model.user.base.UsersRepository

class UsersPresenter(private val view: UsersContract.View):
    UsersContract.Presenter {

    override fun getUsers(usersRepository: UsersRepository) {
        view.showLoadingIndicator(true)
        usersRepository
            .getUsers({
                view.showLoadingIndicator(false)
                view.showUsers(it)
            }, {
                view.showLoadingIndicator(false)
                view.showErrorMessage(it)
            })
    }

    override fun openUserPhoto(viewToAnimate: android.view.View, url: String) {
        view.startPhotoActivity(viewToAnimate, url)
    }
}