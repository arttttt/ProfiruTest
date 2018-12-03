package com.arttttt.profirumvp

import com.arttttt.profirumvp.model.photo.Photo
import com.arttttt.profirumvp.model.photo.base.PhotoRepository
import com.arttttt.profirumvp.presenter.photo.PhotoContract
import com.arttttt.profirumvp.presenter.photo.PhotoPresenter
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class PhotoPresenterTest {
    private val PHOTO_URI = "https://vk.com/images/camera_200.png"

    private lateinit var presenter: PhotoPresenter

    @RelaxedMockK
    private lateinit var view: PhotoContract.View

    @RelaxedMockK
    private lateinit var repository: PhotoRepository

    @RelaxedMockK
    private lateinit var photo: Photo

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { repository.getPhoto(captureLambda(), captureLambda(), PHOTO_URI) } answers {
            val completion = firstArg<(Photo) -> Unit>()
            completion.invoke(photo)
        }
        presenter = spyk(PhotoPresenter(view, repository))
    }

    @Test
    fun callFinishTest() {
        presenter.onPhotoClicked()
        verify { view.destroyView() }
    }

    @Test
    fun loadPhotoTest() {
        presenter.loadPhoto(PHOTO_URI)
        verify{ view.setPhoto(photo) }
    }
}