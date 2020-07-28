package tin.novakovic.dogwatch.ui.main

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tin.novakovic.dogwatch.DisposingViewModel
import tin.novakovic.core.use_case_layer.DogHelper
import javax.inject.Inject

class MainViewModel @Inject constructor(private val dogHelper: DogHelper) : DisposingViewModel() {

    val viewState = MutableLiveData<MainViewState>()

    fun onInitView() {
        fetchDogs()
    }

    private fun fetchDogs() {
        add(dogHelper.fetchAllDogs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
