package com.arttttt.profirumvp

import com.arttttt.profirumvp.presenter.login.LoginContract
import com.arttttt.profirumvp.presenter.login.LoginPresenter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LoginPresenterTest {
    private lateinit var presenter: LoginPresenter

    @RelaxedMockK
    private lateinit var view: LoginContract.View

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = spyk(LoginPresenter(view))
    }

    @Test
    fun startLoginProcessTest() {
        presenter.onLoginButtonClicked()
        verify { view.startLoginProcess() }
    }
}