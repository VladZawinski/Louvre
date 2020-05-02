package non.shahad.stayhomegallery.ui.home

import androidx.lifecycle.*
import com.dropbox.android.external.store4.*
import kotlinx.coroutines.*
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.repository.PostRepository
import non.shahad.stayhomegallery.utils.ext.timberD
import non.shahad.stayhomegallery.utils.network.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val postRepo : PostRepository
) : ViewModel() {

    var isLoading = false
    val unsplashResponse = MutableLiveData<Resource<List<Post>>>()

    val freshResponse = MutableLiveData<List<Post>>()

    fun fetchUnsplashStore(page : Long){
        isLoading = true
        viewModelScope.launch {
            try {
                // Cache
                val posts = withContext(Dispatchers.IO){
                    postRepo.fetchUnsplash(page).get(page)
                }

                if (posts.isNotEmpty()){
                    unsplashResponse.postValue(Resource.success(posts))
                    isLoading = false
                    return@launch
                }

                val fresh = withContext(Dispatchers.IO){
                    postRepo.fetchUnsplash(page).fresh(page)
                }

                unsplashResponse.postValue(Resource.success(fresh))
                isLoading = false


            }catch (e : Throwable){
                unsplashResponse.postValue(Resource.error(e.message!!,null))
                isLoading = false
            }

        }
    }

    @ExperimentalStoreApi
    fun refresh(){
        isLoading = true
        viewModelScope.launch {
            isLoading = try {
                val cache = postRepo.fetchUnsplash(1).get(1)
                freshResponse.postValue(cache)
                val fresh = postRepo.fetchUnsplash(1).fresh(1)
                freshResponse.postValue(fresh)
                false
            }catch (e : Throwable){
                timberD("Home_","$e")
                false
            }
        }
    }

}