package tin.novakovic.dogwatch.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {

    @GET("breeds/list/all")
    fun fetchAllBreeds(): Single<DogResponse>

    @GET("breed/{breed}/images/random/{quantity}")
    fun fetchImagesByBreed(
        @Path("breed") breed: String,
        @Path("quantity") quantity: Int
    ): Single<DogImages>

    @GET("breed/{breed}/{subBreed}/images/random/{quantity}")
    fun fetchImagesBySubBreed(
        @Path("breed") breed: String,
        @Path("subBreed") subBreed: String,
        @Path("quantity") quantity: Int
    ): Single<DogImages>
}

