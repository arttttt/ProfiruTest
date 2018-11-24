package com.arttttt.profirumvp.model.data

import com.arttttt.profirumvp.model.data.base.Repository
import com.arttttt.profirumvp.model.User
import com.vk.sdk.api.*
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKList

object UsersRepositoryImpl: Repository<List<User>> {

    override fun loadAsync(callback: Repository.RepositoryLoadAsyncCallback<List<User>>) {
        VKApi
            .friends()
            .get(VKParameters.from(VKApiConst.FIELDS,"id,first_name,last_name,photo_id,photo_max_orig"))
            .executeWithListener(object: VKRequest.VKRequestListener() {
                override fun onComplete(response: VKResponse?) {
                    super.onComplete(response)

                    val list = response?.parsedModel as VKList<VKApiUser>

                    val users: MutableList<User> = mutableListOf()
                    list.forEach {
                        users.add(User(it.first_name, it.last_name, it.photo_max_orig, null))
                    }

                    callback.onDataLoaded(users)
                }

                override fun onError(error: VKError?) {
                    super.onError(error)

                    if (error != null)
                        callback.onError(error.errorMessage)
                }
            })
    }
}