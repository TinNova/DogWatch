package tin.novakovic.dogwatch.api
import com.google.gson.annotations.SerializedName


data class DogImages(
    @SerializedName("message")
    val imageUrls: List<String> = listOf()
)