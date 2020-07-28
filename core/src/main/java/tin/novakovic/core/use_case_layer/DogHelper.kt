package tin.novakovic.core.use_case_layer

import io.reactivex.Single
import tin.novakovic.core.domain_layer.Dog
import tin.novakovic.core.data_layer.DogRepo
import javax.inject.Inject

const val QUANTITY_OF_IMAGES = 10

class DogHelper @Inject constructor(private val dogRepo: DogRepo) {

    fun fetchAllDogs(): Single<List<Dog>> =
        dogRepo.fetchDogs()
            .flattenAsObservable { it.message.entries }
            .map { convertMapToList(it) }
            .flatMapIterable { it }
            .toList()

    fun fetchDogImages(dog: Dog): Single<List<String>> =
        if (dog.subBreed.isBlank()) {
            fetchImagesByBreed(dog.breed)
        } else {
            fetchImagesBySubBreed(dog.breed, dog.subBreed)
        }

    private fun convertMapToList(mapEntry: Map.Entry<String, List<String>>): MutableList<Dog> {
        var dogs: MutableList<Dog> = mutableListOf()

        return if (mapEntry.value.isNullOrEmpty()) {
            dogs.add(Dog(mapEntry.key))
            dogs

        } else {
            mapEntry.value.forEach {
                dogs.add(Dog(mapEntry.key, "$it "))
            }
            dogs
        }
    }

    private fun fetchImagesByBreed(breed: String): Single<List<String>> =
        dogRepo.fetchImagesByBreed(breed,
            QUANTITY_OF_IMAGES
        )
            .map { it.imageUrls }

    private fun fetchImagesBySubBreed(breed: String, subBreed: String): Single<List<String>> =
        dogRepo.fetchImagesBySubBreed(
            breed, subBreed.trim(),
            QUANTITY_OF_IMAGES
        )
            .map { it.imageUrls }

}
