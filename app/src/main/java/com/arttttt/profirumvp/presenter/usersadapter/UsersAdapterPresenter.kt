package com.arttttt.profirumvp.presenter.usersadapter

import com.arttttt.profirumvp.model.photo.base.PhotoRepository
import com.arttttt.profirumvp.model.user.User

class UsersAdapterPresenter(private val view: UsersAdapterContract.View,
                            private val photoRepository: PhotoRepository):
    UsersAdapterContract.Presenter {

    private val users = mutableListOf<User>()

    override fun bind(position: Int, holder: UsersAdapterContract.ViewHolder) {
        val user = getItemAt(position)

        holder.setFirstName(user.firstName)
        holder.setLastName(user.lastName)

        photoRepository.getPhoto({
            holder.showLoadingIndicator(false)
            holder.setPhotoImage(it)
        },{}, user.photoUrl)
    }

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