package tin.novakovic.dogwatch.ui.main

import androidx.lifecycle.MutableLiveData
import tin.novakovic.dogwatch.DisposingViewModel
import tin.novakovic.dogwatch.DogHelper
import javax.inject.Inject

class MainViewModel @Inject constructor(private val dogHelper: DogHelper) : DisposingViewModel() {

    val viewState = MutableLiveData<MainViewState>()

    fun onInitView() {
        fetchDogs()
    }

    private fun fetchDogs() {
        add(dogHelper.fetchAllDogs()
            .subscribe({
                viewState.value =
                    MainViewState(
                        dogs = it,
                        isPresenting = true,
                        isLoading = false,
                        isError = false
                    )
            },{
                viewState.value =
                    MainViewState(
                        isPresenting = false,
                        isLoading = false,
                        isError = true
                    )
            }))
    }

}
