package non.shahad.stayhomegallery.data.remote

import non.shahad.stayhomegallery.data.model.CollectionResponse
import non.shahad.stayhomegallery.data.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LouvreAPI {

    @GET("unsplash/photos")
    suspend fun getUnsplashImages(
        @Query("page") page : Long
    ) : UnsplashResponse

    @GET("unsplash/collections")
    suspend fun getCollections(
        @Query("page") page : Long
    ) : CollectionResponse

}