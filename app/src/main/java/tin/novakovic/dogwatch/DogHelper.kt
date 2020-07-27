package tin.novakovic.dogwatch

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tin.novakovic.dogwatch.api.Dog
import tin.novakovic.dogwatch.api.DogImages
import javax.inject.Inject

const val QUANTITY_OF_IMAGES = 10

class DogHelper @Inject constructor(private val dogRepo: DogRepo) {

    fun fetchAllDogs(): Single<List<Dog>> =
        dogRepo.fetchDogs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flattenAsObservable { it.message.entries }
            .map { convertEntryToDogList(it) }
            .map { it.listIterator().next() }
            .toList()


    private fun convertEntryToDogList(mapEntry: Map.Entry<String, List<String>>): List<Dog> =
        mapEntry.value.ifEmpty { listOf(null) }
            .map { subBreed ->
                Dog(
                    mapEntry.key,
                    subBreed
                )
            }

    fun fetchDogImages(dog: Dog): Single<List<String>> =
        if (dog.subBreed == null) {
            fetchImagesByBreed(dog.breed)
        } else {
            fetchImagesBySubBreed(dog.breed, dog.subBreed)
        }

    private fun fetchImagesByBreed(breed: String): Single<List<String>> =
        dogRepo.fetchImagesByBreed(breed, QUANTITY_OF_IMAGES)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.imageUrls }

    private fun fetchImagesBySubBreed(breed: String, subBreed: String): Single<List<String>> =
        dogRepo.fetchImagesBySubBreed(breed, subBreed, QUANTITY_OF_IMAGES)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.imageUrls }

}
