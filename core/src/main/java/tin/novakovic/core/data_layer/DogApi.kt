package tin.novakovic.core.data_layer

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import tin.novakovic.core.domain_layer.DogImagesResponse
import tin.novakovic.core.domain_layer.DogResponse

interface DogApi {

    @GET("breeds/list/all")
    fun fetchAllBreeds(): Single<DogResponse>

    @GET("breed/{breed}/images/random/{quantity}")
    fun fetchImagesByBreed(
        @Path("breed") breed: String,
        @Path("quantity") quantity: Int
    ): Single<DogImagesResponse>

    @GET("breed/{breed}/{subBreed}/images/random/{quantity}")
    fun fetchImagesBySubBreed(
        @Path("breed") breed: String,
        @Path("subBreed") subBreed: String,
        @Path("quantity") quantity: Int
    ): Single<DogImagesResponse>
}

