package non.shahad.stayhomegallery.di.module

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import non.shahad.stayhomegallery.data.remote.LouvreAPI
import non.shahad.stayhomegallery.utils.constants.Network
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client : OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Network.BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideLouvreAPI(retrofit: Retrofit) : LouvreAPI{
        return retrofit.create(LouvreAPI::class.java)
    }
}