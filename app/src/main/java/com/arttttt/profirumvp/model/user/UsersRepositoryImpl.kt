package com.arttttt.profirumvp.model.user

import com.arttttt.profirumvp.model.user.base.UsersDataSource
import com.arttttt.profirumvp.model.user.base.UsersRepository

class UsersRepositoryImpl(private val dataSource: UsersDataSource): UsersRepository {
    private val users = linkedSetOf<User>()

    override fun getUsers(onCompletion: (List<User>) -> Unit, onError: (String) -> Unit) {
        if (users.isNotEmpty()) {
            onCompletion(users.toList())
            return
        }

        dataSource.get({
            users.addAll(it)
            onCompletion(it)
        }, {
            onError(it)
        })
    }
}