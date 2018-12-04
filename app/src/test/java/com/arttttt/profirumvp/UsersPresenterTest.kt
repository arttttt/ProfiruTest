package com.arttttt.profirumvp

import com.arttttt.profirumvp.model.user.User
import com.arttttt.profirumvp.model.user.base.UsersRepository
import com.arttttt.profirumvp.presenter.users.UsersContract
import com.arttttt.profirumvp.presenter.users.UsersPresenter
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class UsersPresenterTest {
    private val PHOTO_URI = "https://vk.com/images/camera_200.png"
    private val ERROR_TEXT = "SAMPLE ERROR TEXT"

    private val users = listOf(User("Test1", "Test1_last", PHOTO_URI),
        User("Test2", "Test2_last", ""),
        User("Test3", "Test3_last", ""))

    private lateinit var presenter: UsersPresenter

    @RelaxedMockK
    private lateinit var view: UsersContract.View

    @RelaxedMockK
    private lateinit var repository: UsersRepository

    private val onCompletion = slot<(List<User>) -> Unit>()
    private val onError = slot<(String) -> Unit>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { repository.getUsers(onCompletion = capture(onCompletion), onError = capture(onError)) } answers {
            val onCompletion = firstArg<(List<User>) -> Unit>()
            onCompletion.invoke(users)

            val onError = secondArg<(String) -> Unit>()
            onError.invoke(ERROR_TEXT)
        }
        presenter = spyk(UsersPresenter(view, repository))
    }

    @Test
    fun openUserPhotoTest() {
        val sharedView = 1
        presenter.openUserPhoto(sharedView, 0, "")
        verify { view.startPhotoActivity(sharedView, 0, "") }
    }

    @Test
    fun getUsersTest() {
        presenter.getUsers()
        verifyOrder {
            view.showLoadingIndicator(true)
            repository.getUsers(onCompletion.captured, onError.captured)
            view.showLoadingIndicator(false)
            view.showUsers(users)
            view.showLoadingIndicator(false)
            view.showErrorMessage(ERROR_TEXT)
        }
    }

    @Test
    fun usersRepositoryCallbacksTest() {
        repository.getUsers({
            assert(it.size == 3)
            assert(it[0].photoUrl == PHOTO_URI)
            assert(it[0].firstName == "Test1")
            assert(it[0].lastName == "Test1_last")
        }, {
            assert(it == ERROR_TEXT)
        })
    }
}