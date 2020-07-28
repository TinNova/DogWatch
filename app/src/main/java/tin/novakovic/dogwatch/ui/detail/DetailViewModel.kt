package tin.novakovic.dogwatch.ui.detail

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tin.novakovic.core.domain_layer.Dog
import tin.novakovic.core.use_case_layer.DogHelper
import tin.novakovic.dogwatch.DisposingViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val dogHelper: DogHelper) : DisposingViewModel() {

    val viewState = MutableLiveData<DetailViewState>()

    fun onInitView(dog: Dog) {
        fetchDogImages(dog)
    }

    private fun fetchDogImages(dog: Dog) {
        add(dogHelper.fetchDogImages(dog)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
