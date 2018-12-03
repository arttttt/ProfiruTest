package com.arttttt.profirumvp

import android.view.View
import com.arttttt.profirumvp.model.user.base.UsersRepository
import com.arttttt.profirumvp.presenter.users.UsersContract
import com.arttttt.profirumvp.presenter.users.UsersPresenter
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class UsersPresenterTest {
    private lateinit var presenter: UsersPresenter

    @RelaxedMockK
    private lateinit var view: UsersContract.View

    @RelaxedMockK
    private lateinit var repository: UsersRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = spyk(UsersPresenter(view, repository))
    }

    @Test
    fun openUserPhotoTest() {
        val sharedView = mockk<View>()
        presenter.openUserPhoto(sharedView, "")
        verify { view.startPhotoActivity(sharedView, "") }
    }

    @Test
    fun getUsersTest() {
        presenter.getUsers()
        verify { view.showLoadingIndicator(true) }
        repository.getUsers({
            verifyOrder {
                view.showLoadingIndicator(false)
                view.showUsers(it)
            }
        }, {
            verifyOrder {
                view.showLoadingIndicator(false)
                view.showErrorMessage(it)
            }
        })
    }
}