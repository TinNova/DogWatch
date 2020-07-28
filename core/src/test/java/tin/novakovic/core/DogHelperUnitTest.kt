package tin.novakovic.core

import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import tin.novakovic.core.domain_layer.Dog
import tin.novakovic.core.domain_layer.DogImagesResponse
import tin.novakovic.core.domain_layer.DogResponse
import tin.novakovic.core.data_layer.DogRepo
import tin.novakovic.core.use_case_layer.DogHelper

class DogHelperUnitTest {

    @Mock
    lateinit var mockDogRep: DogRepo

    private lateinit var target: DogHelper

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = DogHelper(mockDogRep)
    }

    @Test
    fun fetchAllDogs_returnSingleOfListOfDogs() {
        //given
        val mockDogResponse = DogResponse(
            message = mapOf(
                "Boxer" to listOf("German", "Spanish"),
                "Shepard" to listOf("English", "Welsh", "Scottish"),
                "Husky" to listOf()
            )
        )

        val mockDogs = listOf(
            Dog("Boxer", "German "),
            Dog("Boxer", "Spanish "),
            Dog("Shepard", "English "),
            Dog("Shepard", "Welsh "),
            Dog("Shepard", "Scottish "),
            Dog("Husky", "")
        )

        whenever(mockDogRep.fetchDogs()).thenReturn(Single.just(mockDogResponse))

        //when
        val returnValue = target.fetchAllDogs().test()

        //then
        val result = returnValue.values().first()
        assertEquals(result, mockDogs)
    }

    @Test
    fun fetchDogImages_fetchImagesByBreed_returnListOfStringUrls() {
        //given
        val mockBreed = "Husky"
        val quantity = 10
        val mockDog = Dog(mockBreed)
        val mockDogImagesResponse = DogImagesResponse(listOf("url1", "url2", "url3"))
        val mockDogImages = listOf("url1", "url2", "url3")

        whenever(mockDogRep.fetchImagesByBreed(mockBreed, quantity)).thenReturn(Single.just(mockDogImagesResponse))

        //when
        val returnValue = target.fetchDogImages(mockDog).test()

        //then
        val result = returnValue.values().first()
        assertEquals(result, mockDogImages)
    }

    @Test
    fun fetchDogImages_fetchImagesBySubBreed_returnListOfStringUrls() {
        //given
        val mockBreed = "Bulldog"
        val mockSubBreed = "English"
        val quantity = 10
        val mockDog =
            Dog(mockBreed, mockSubBreed)
        val mockDogImagesResponse = DogImagesResponse(listOf("url1", "url2", "url3"))
        val mockDogImages = listOf("url1", "url2", "url3")

        whenever(mockDogRep.fetchImagesBySubBreed(mockBreed, mockSubBreed, quantity)).thenReturn(Single.just(mockDogImagesResponse))

        //when
        val returnValue = target.fetchDogImages(mockDog).test()

        //then
        val result = returnValue.values().first()
        assertEquals(result, mockDogImages)
    }

}
