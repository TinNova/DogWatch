package tin.novakovic.dogwatch.ui.detail

import tin.novakovic.dogwatch.api.DogImages

data class DetailViewState(

    val imageUrls: List<String> = emptyList(),
    val isPresenting: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false

)