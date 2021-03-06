package tin.novakovic.dogwatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import tin.novakovic.core.domain_layer.Dog
import tin.novakovic.core.use_case_layer.DogHelper
import tin.novakovic.dogwatch.ui.main.MainViewModel
import tin.novakovic.dogwatch.ui.main.MainViewState

class MainViewModelUnitTest {

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

    private lateinit var target: MainViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        target = MainViewModel(mockDogHelper)
    }

    @Test
    fun init_onInitView_fetchDogs_Success_viewStateEquals_dogsTrueFalseFalse() {
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
    fun init_onInitView_fetchDogs_Failure_viewStateEquals_falseFalseTrue() {
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
