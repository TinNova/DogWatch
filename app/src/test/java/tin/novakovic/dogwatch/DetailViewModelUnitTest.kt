package tin.novakovic.dogwatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import tin.novakovic.core.domain_layer.Dog
import tin.novakovic.core.use_case_layer.DogHelper
import tin.novakovic.dogwatch.ui.detail.DetailViewModel
import tin.novakovic.dogwatch.ui.detail.DetailViewState

class DetailViewModelUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    lateinit var mockDogHelper: DogHelper

    private lateinit var target: DetailViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = DetailViewModel(mockDogHelper)
    }

    @Test
    fun onInitView_fetchDogImages_successful_viewStateEquals_ImageTrueFalseFalse() {
        //given
        val mockDog = mock<Dog>()
        val mockUrls = mock<List<String>>()

        whenever(mockDogHelper.fetchDogImages(mockDog)).thenReturn(Single.just(mockUrls))

        //when
        target.onInitView(mockDog)

        //then
        assertEquals(
            DetailViewState(
                mockUrls,
                isPresenting = true,
                isLoading = false,
                isError = false
            ), target.viewState.value
        )
    }

    @Test
    fun onInitView_fetchDogImages_failure_viewStateEquals_TrueFalseFalse() {
        //given
        val mockDog = mock<Dog>()

        whenever(mockDogHelper.fetchDogImages(mockDog)).thenReturn(Single.error(Throwable()))

        //when
        target.onInitView(mockDog)

        //then
        assertEquals(
            DetailViewState(
                isPresenting = false,
                isLoading = false,
                isError = true
            ), target.viewState.value
        )
    }

}
