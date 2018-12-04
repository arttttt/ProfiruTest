package com.arttttt.profirumvp.di

import com.arttttt.profirumvp.model.photo.PhotoDataSourceImpl
import com.arttttt.profirumvp.model.photo.PhotoRepositoryImpl
import com.arttttt.profirumvp.model.photo.base.PhotoDataSource
import com.arttttt.profirumvp.model.photo.base.PhotoRepository
import com.arttttt.profirumvp.model.user.UsersDataSourceImpl
import com.arttttt.profirumvp.model.user.UsersRepositoryImpl
import com.arttttt.profirumvp.model.user.base.UsersDataSource
import com.arttttt.profirumvp.model.user.base.UsersRepository
import com.arttttt.profirumvp.presenter.login.LoginContract
import com.arttttt.profirumvp.presenter.login.LoginPresenter
import com.arttttt.profirumvp.presenter.photo.PhotoContract
import com.arttttt.profirumvp.presenter.photo.PhotoPresenter
import com.arttttt.profirumvp.presenter.users.UsersContract
import com.arttttt.profirumvp.presenter.users.UsersPresenter
import com.arttttt.profirumvp.presenter.usersadapter.UsersAdapterContract
import com.arttttt.profirumvp.presenter.usersadapter.UsersAdapterPresenter
import org.koin.dsl.module.module

object AppModule {
    val module = module {
        single<PhotoDataSource> { PhotoDataSourceImpl() }
        single<PhotoRepository> { PhotoRepositoryImpl(get()) }

        single<UsersDataSource> { UsersDataSourceImpl() }
        single<UsersRepository> { UsersRepositoryImpl(get()) }

        factory<PhotoContract.Presenter> { (view: PhotoContract.View) -> PhotoPresenter(view, get()) }
        factory<LoginContract.Presenter> { (view: LoginContract.View) -> LoginPresenter(view) }
        factory<UsersContract.Presenter> { (view: UsersContract.View) -> UsersPresenter(view, get()) }
        factory<UsersAdapterContract.Presenter> { (view: UsersAdapterContract.View) -> UsersAdapterPresenter(view, get()) }
    }
}