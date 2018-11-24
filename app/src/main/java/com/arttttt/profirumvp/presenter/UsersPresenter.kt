package com.arttttt.profirumvp.presenter

import com.arttttt.profirumvp.model.data.UsersRepositoryImpl
import com.arttttt.profirumvp.model.data.base.Repository
import com.arttttt.profirumvp.model.User

class UsersPresenter(private val view: UsersContract.View):
    UsersContract.Presenter {
    override fun getUsers() {
        view.showLoadingProgressbar(false)
        UsersRepositoryImpl.loadAsync(object: Repository.RepositoryLoadAsyncCallback<List<User>> {
            override fun onDataLoaded(data: List<User>) {
                view.showLoadingProgressbar(true)
                view.showUsers(data)
            }

            override fun onError(message: String) {
                view.showLoadingProgressbar(true)
                view.showErrorMessage(message)
            }
        })
    }

    override fun openUserPhoto(viewToAnimate: android.view.View, url: String) {
        view.startPhotoActivity(viewToAnimate, url)
    }
}