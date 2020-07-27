package tin.novakovic.dogwatch.ui.detail

import androidx.lifecycle.MutableLiveData
import tin.novakovic.dogwatch.DisposingViewModel
import tin.novakovic.dogwatch.DogHelper
import tin.novakovic.dogwatch.api.Dog
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val dogHelper: DogHelper) : DisposingViewModel() {

    val viewState = MutableLiveData<DetailViewState>()
    private var dogs = mutableListOf<Dog>()

    fun onInitView(dog: Dog) {
        fetchDogImages(dog)
    }

    private fun fetchDogImages(dog: Dog) {
        add(dogHelper.fetchDogImages(dog)
            .subscribe({
                viewState.value =
                    DetailViewState(
                        it,
                        isPresenting = true,
                        isLoading = false,
                        isError = false
                    )
            },{
                viewState.value =
                    DetailViewState(
                        isPresenting = false,
                        isLoading = false,
                        isError = true
                    )
            }))
    }
}
