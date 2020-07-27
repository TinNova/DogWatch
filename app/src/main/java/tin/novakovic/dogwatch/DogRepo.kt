package tin.novakovic.dogwatch

import io.reactivex.Single
import tin.novakovic.dogwatch.api.DogApi
import tin.novakovic.dogwatch.api.DogImages
import tin.novakovic.dogwatch.api.DogResponse
import javax.inject.Inject

class DogRepo @Inject constructor(
    private val dogApi: DogApi
) {

    fun fetchDogs(): Single<DogResponse> = dogApi.fetchAllBreeds()

    fun fetchImagesByBreed(breed: String, quantityOfImages: Int): Single<DogImages> =
        dogApi.fetchImagesByBreed(breed, quantityOfImages)

    fun fetchImagesBySubBreed(
        breed: String,
        subBreed: String,
        quantityOfImages: Int
    ): Single<DogImages> = dogApi.fetchImagesBySubBreed(breed, subBreed, quantityOfImages)

}
