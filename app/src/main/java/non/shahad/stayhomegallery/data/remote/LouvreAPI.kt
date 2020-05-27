package non.shahad.stayhomegallery.data.remote

import non.shahad.stayhomegallery.data.model.CollectionDetailResponse
import non.shahad.stayhomegallery.data.model.CollectionResponse
import non.shahad.stayhomegallery.data.model.SearchResponse
import non.shahad.stayhomegallery.data.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LouvreAPI {

    @GET("unsplash/photos")
    suspend fun getUnsplashImages(
        @Query("page") page : Long,
        @Query("order_by")orderBy: String
    ) : UnsplashResponse

    @GET("unsplash/collections")
    suspend fun getCollections(
        @Query("page") page : Long
    ) : CollectionResponse

    @GET("search")
    suspend fun search(
        @Query("q")query: String,
        @Query("page")page: Long
    ) : SearchResponse

    @GET("unsplash/collections/{id}")
    suspend fun getCollectionDetail(
        @Path("id")id: Long,
        @Query("page")page: Long
    ) : CollectionDetailResponse
}