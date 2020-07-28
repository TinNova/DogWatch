package tin.novakovic.dogwatch.ui.detail

data class DetailViewState(

    val imageUrls: List<String> = emptyList(),
    val isPresenting: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false

)