package com.anestevez.gigirestaurants

import android.app.Application
import com.anestevez.gigirestaurants.core.PlayServicesLocationDataSource
import com.anestevez.gigirestaurants.core.location.LocationDataSource
import com.anestevez.gigirestaurants.core.remote.GigiRemoteDataSource
import com.anestevez.gigirestaurants.core.remote.RemoteService
import com.anestevez.gigirestaurants.data.datasources.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(): String = BuildConfig.API_KEY
    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(application: Application): String =
        application.getString(R.string.tripadvisor_base_url)

    @Provides
    @Singleton
    fun okHttpClientProvider(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }
    @Provides
    @Singleton
    fun remoteServiceProvider(
        okHttpClient: OkHttpClient,
        @Named("baseUrl") baseUrl: String,
    ): RemoteService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()


}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {
    @Binds
    internal abstract fun bindRemoteDataSource(remoteDataSource: GigiRemoteDataSource): RemoteDataSource

    @Binds
    internal abstract fun bindLocationDataSource(locationDataSource: PlayServicesLocationDataSource): LocationDataSource

}
