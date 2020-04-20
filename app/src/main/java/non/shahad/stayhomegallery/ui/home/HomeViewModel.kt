package non.shahad.stayhomegallery.ui.home

import androidx.lifecycle.*
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.local.mapper.mapToUnsplashResponse
import non.shahad.stayhomegallery.data.model.ErrorResponse
import non.shahad.stayhomegallery.data.model.UnsplashResponse
import non.shahad.stayhomegallery.data.repository.PostRepository
import non.shahad.stayhomegallery.utils.network.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val postRepo : PostRepository
) : ViewModel() {

    var isLoading = false

    val unsplashResponse = MutableLiveData<NetworkResponse<UnsplashResponse,ErrorResponse>>()


    fun fetchUnsplash(page : Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                isLoading = true
                val result = postRepo.fetchPostsFromUnsplash(page)
                unsplashResponse.postValue(result)
                isLoading = false
            }
        }
    }



}