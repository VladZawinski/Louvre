package non.shahad.stayhomegallery.ui.home

import androidx.lifecycle.*
import com.dropbox.android.external.store4.*
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.*
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.model.ErrorResponse
import non.shahad.stayhomegallery.data.model.UnsplashResponse
import non.shahad.stayhomegallery.data.repository.BookmarkRepository
import non.shahad.stayhomegallery.data.repository.PostRepository
import non.shahad.stayhomegallery.utils.ext.timberD
import non.shahad.stayhomegallery.utils.network.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val postRepo : PostRepository,
    private val bookmarkRepo : BookmarkRepository
) : ViewModel() {

    var isLoading = false
    val unsplashResponse = MutableLiveData<Resource<List<Post>>>()

    fun fetchUnsplashStore(page : Long){
        isLoading = true
        viewModelScope.launch {
            try {
                // Cache
                val posts = postRepo.fetchUnsplash(page).get(page)
                if (posts.isNotEmpty()){
                    unsplashResponse.postValue(Resource.success(posts))
                    isLoading = false
                    return@launch
                }

                val fresh = postRepo.fetchUnsplash(page).fresh(page)
                unsplashResponse.postValue(Resource.success(fresh))
                isLoading = false


            }catch (e : Throwable){
                unsplashResponse.postValue(Resource.error(e.message!!,null))
                isLoading = false
            }

        }
    }

    fun insertIntoBookmark(post: Post){
        viewModelScope.launch {
            bookmarkRepo.insertIntoBookmark(post)
        }
    }
}