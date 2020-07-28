package tin.novakovic.core.domain_layer
import com.google.gson.annotations.SerializedName


data class DogImagesResponse(
    @SerializedName("message")
    val imageUrls: List<String> = listOf()
)