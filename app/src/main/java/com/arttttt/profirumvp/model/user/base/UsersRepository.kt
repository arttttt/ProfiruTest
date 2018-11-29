package com.arttttt.profirumvp.model.user.base

import com.arttttt.profirumvp.model.user.User

interface UsersRepository {
    fun getUsers(onCompletion: (List<User>) -> Unit, onError: (String) -> Unit)
}