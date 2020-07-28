package tin.novakovic.dogwatch.ui.main

import tin.novakovic.core.domain_layer.Dog


interface MainClickListener {

    fun onDogClicked(dog: Dog)

}