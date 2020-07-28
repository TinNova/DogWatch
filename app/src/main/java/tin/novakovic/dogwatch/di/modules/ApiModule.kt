package tin.novakovic.dogwatch.di.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tin.novakovic.core.data_layer.DogApi

@Module
class ApiModule {

    @Provides
    @Reusable
    fun providesRetrofit(okHttpClient: OkHttpClient.Builder): DogApi =
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .client(okHttpClient
                .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DogApi::class.java)

    @Provides
    @Reusable
    fun providesOkHttpClient(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

}
