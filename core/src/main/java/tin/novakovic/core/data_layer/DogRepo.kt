package tin.novakovic.core.data_layer

import io.reactivex.Single
import tin.novakovic.core.domain_layer.DogImagesResponse
import tin.novakovic.core.domain_layer.DogResponse
import javax.inject.Inject

class DogRepo @Inject constructor(private val dogApi: DogApi) {

    fun fetchDogs(): Single<DogResponse> = dogApi.fetchAllBreeds()

    fun fetchImagesByBreed(breed: String, quantityOfImages: Int): Single<DogImagesResponse> =
        dogApi.fetchImagesByBreed(breed, quantityOfImages)

    fun fetchImagesBySubBreed(
        breed: String,
        subBreed: String,
        quantityOfImages: Int
    ): Single<DogImagesResponse> = dogApi.fetchImagesBySubBreed(breed, subBreed, quantityOfImages)

}
