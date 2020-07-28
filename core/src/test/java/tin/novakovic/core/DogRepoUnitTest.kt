package tin.novakovic.core

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import tin.novakovic.core.data_layer.DogApi
import tin.novakovic.core.domain_layer.DogImagesResponse
import tin.novakovic.core.domain_layer.DogResponse
import tin.novakovic.core.data_layer.DogRepo

class DogRepoUnitTest {

    @Mock
    lateinit var mockDogApi: DogApi

    private lateinit var target: DogRepo

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = DogRepo(mockDogApi)
    }

    @Test
    fun fetchDogs_returnDogResponse() {
        //given
        val mockResponse = mock<DogResponse>()
        whenever(mockDogApi.fetchAllBreeds()).thenReturn(Single.just(mockResponse))

        //when
        val returnValue = target.fetchDogs().test()

        //then
        val result = returnValue.values().first()
        assertEquals(result, mockResponse)
    }

    @Test
    fun fetchImagesByBreed_returnDogImagesResponse() {
        //given
        val mockBreed = "Husky"
        val mockQuantity = 10
        val mockResponse = mock<DogImagesResponse>()

        whenever(mockDogApi.fetchImagesByBreed(mockBreed, mockQuantity)).thenReturn(
            Single.just(
                mockResponse
            )
        )

        //when
        val returnValue = target.fetchImagesByBreed(mockBreed, mockQuantity).test()

        //then
        val result = returnValue.values().first()
        assertEquals(result, mockResponse)
    }

    @Test
    fun fetchImagesBySubBreed_returnDogImagesResponse() {
        //given
        val mockBreed = "Bulldog"
        val mockSubBreed = "English"
        val mockQuantity = 10
        val mockResponse = mock<DogImagesResponse>()

        whenever(mockDogApi.fetchImagesBySubBreed(mockBreed, mockSubBreed, mockQuantity))
            .thenReturn(Single.just(mockResponse))

        //when
        val returnValue = target.fetchImagesBySubBreed(mockBreed, mockSubBreed, mockQuantity).test()

        //then
        val result = returnValue.values().first()
        assertEquals(result, mockResponse)
    }

}
