package com.arttttt.profirumvp.model.user.base

import com.arttttt.profirumvp.model.base.DataSourceBase
import com.arttttt.profirumvp.model.user.User

abstract class UsersDataSource: DataSourceBase<Unit, List<User>>()