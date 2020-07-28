package tin.novakovic.dogwatch.ui.main

import tin.novakovic.core.domain_layer.Dog


data class MainViewState(

    val dogs: List<Dog> = emptyList(),
    val isPresenting: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = false

)
