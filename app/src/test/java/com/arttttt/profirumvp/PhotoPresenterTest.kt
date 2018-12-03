package com.arttttt.profirumvp

import com.arttttt.profirumvp.model.photo.base.PhotoRepository
import com.arttttt.profirumvp.presenter.photo.PhotoContract
import com.arttttt.profirumvp.presenter.photo.PhotoPresenter
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class PhotoPresenterTest {
    private lateinit var presenter: PhotoPresenter

    @RelaxedMockK
    private lateinit var view: PhotoContract.View

    @RelaxedMockK
    private lateinit var repository: PhotoRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = spyk(PhotoPresenter(view, repository))
    }

    @Test
    fun callFinishTest() {
        presenter.onPhotoClicked()
        verify { view.destroyView() }
    }

    @Test
    fun loadPhotoTest() {
        presenter.loadPhoto("")
        repository.getPhoto({
            verify { view.setPhoto(it) }
        }, {}, "")
    }
}