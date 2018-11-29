package com.arttttt.profirumvp.model.user

import com.arttttt.profirumvp.base.SingletonHolderWithParam
import com.arttttt.profirumvp.model.user.base.UsersDataSource
import com.arttttt.profirumvp.model.user.base.UsersRepository

class UsersRepositoryImpl private constructor(private val dataSource: UsersDataSource):
    UsersRepository {

    companion object: SingletonHolderWithParam<UsersDataSourceImpl, UsersRepositoryImpl>(::UsersRepositoryImpl)

    private val users = mutableListOf<User>()

    override fun getUsers(onCompletion: (List<User>) -> Unit, onError: (String) -> Unit) {
        if (users.isNotEmpty()) {
            onCompletion(users)
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