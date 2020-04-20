package non.shahad.stayhomegallery.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.dao.PostDao
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.local.mapper.mapToUnsplashResponse
import non.shahad.stayhomegallery.data.model.ErrorResponse
import non.shahad.stayhomegallery.data.model.UnsplashResponse
import non.shahad.stayhomegallery.data.remote.LouvreAPI
import non.shahad.stayhomegallery.utils.network.Resource
import java.lang.Error
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api : LouvreAPI,
    private val dao : PostDao
) : Repository{

    override var isLoading: Boolean = false

    suspend fun fetchPostsFromUnsplash(page : Long) : NetworkResponse<UnsplashResponse,ErrorResponse>{
        return api.getUnsplashImages(page)
    }



}