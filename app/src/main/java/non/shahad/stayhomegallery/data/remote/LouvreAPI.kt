package non.shahad.stayhomegallery.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import non.shahad.stayhomegallery.data.model.ErrorResponse
import non.shahad.stayhomegallery.data.model.UnsplashResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface LouvreAPI {

    @GET("api/unsplash/{page}")
    suspend fun getUnsplashImages(
        @Path("page") page : Long
    ) : UnsplashResponse

}