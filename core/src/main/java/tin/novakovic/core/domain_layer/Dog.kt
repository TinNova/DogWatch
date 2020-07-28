package tin.novakovic.core.domain_layer

import java.io.Serializable

data class Dog(
    val breed: String,
    val subBreed: String = ""
) : Serializable