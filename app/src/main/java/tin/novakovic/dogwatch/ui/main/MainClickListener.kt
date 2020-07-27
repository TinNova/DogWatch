package tin.novakovic.dogwatch.ui.main

import tin.novakovic.dogwatch.api.Dog

interface MainClickListener {

    fun onDogClicked(dog: Dog)

}