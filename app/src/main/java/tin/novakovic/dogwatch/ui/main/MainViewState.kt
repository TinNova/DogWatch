package tin.novakovic.dogwatch.ui.main

import tin.novakovic.dogwatch.api.Dog


data class MainViewState(

    val dogs: List<Dog> = emptyList(),
    val isPresenting: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false

)
