package com.arttttt.profirumvp.mvp

class UsersViewHolderPresenter(private val view: UsersViewHolderContract.View): UsersViewHolderContract.Presenter {

    var usersAdapterPresenter: UsersAdapterContract.Presenter? = null
    var position: Int? = null

    override fun bind() {
        var user = position?.let {
            usersAdapterPresenter?.getItemAt(it)
        }

        if (user != null) {
            view.setFirstName(user.firstName)
            view.setLastName(user.lastName)
            view.setPhotoImage(user.photoUrl)
        }
    }

    override fun cancelPhotoUpdate(url: String) {
        view.cancelPhotoUpdate(url)
    }

    override fun handleItemClick(view: android.view.View, position: Int) {
        usersAdapterPresenter?.handleItemClick(view, position)
    }
}