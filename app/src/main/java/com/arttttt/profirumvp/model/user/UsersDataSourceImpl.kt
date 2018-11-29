package com.arttttt.profirumvp.model.user

import com.arttttt.profirumvp.model.base.Result
import com.arttttt.profirumvp.model.user.base.UsersDataSource
import com.vk.sdk.api.*
import com.vk.sdk.api.model.VKApiUser
import com.vk.sdk.api.model.VKList

class UsersDataSourceImpl: UsersDataSource() {
    override suspend fun work(param: Unit?): Result {
        lateinit var result: Result

        VKApi
            .friends()
            .get(VKParameters.from(VKApiConst.FIELDS,"id,first_name,last_name,photo_max_orig"))
            .executeSyncWithListener(object: VKRequest.VKRequestListener() {
                override fun onComplete(response: VKResponse) {
                    super.onComplete(response)

                    val list = response.parsedModel as VKList<VKApiUser>

                    val users = list.map { User(
                        it.first_name,
                        it.last_name,
                        it.photo_max_orig
                    ) }

                    result = Result.Success(users)
                }

                override fun onError(error: VKError) {
                    super.onError(error)

                    result = Result.Error(Throwable(error.errorMessage))
                }
            })

        return result
    }
}