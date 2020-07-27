package tin.novakovic.dogwatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import tin.novakovic.dogwatch.api.Dog
import tin.novakovic.dogwatch.ui.main.MainViewModel
import tin.novakovic.dogwatch.ui.main.MainViewState

class MainViewModelUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockDogHelper: DogHelper

    private lateinit var target: MainViewModel

    @Before
    @Throws(Exception::class) //what does this mean?
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = MainViewModel(mockDogHelper)
    }

    @Test
    fun init_fetchDogsSuccess_viewStateEquals_dogsTrueFalseFalse() {
        //given
        val mockDogs = mock<List<Dog>>()
        whenever(mockDogHelper.fetchAllDogs()).thenReturn(Single.just(mockDogs))

        //when
        target.onInitView()

        //then
        assertEquals(
            MainViewState(
                mockDogs,
                isPresenting = true,
                isLoading = false,
                isError = false
            ), target.viewState.value
        )
    }

    @Test
    fun init_fetchDogsFailure_viewStateEquals_falseFalseTrue() {
        //given
        whenever(mockDogHelper.fetchAllDogs()).thenReturn(Single.error(Throwable()))

        //when
        target.onInitView()

        //then
        assertEquals(
            MainViewState(
                isPresenting = false,
                isLoading = false,
                isError = true
            ), target.viewState.value
        )
    }

}
