package non.shahad.stayhomegallery.ui.home

import androidx.lifecycle.*
import com.dropbox.android.external.store4.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.repository.home.HomeRepository
import non.shahad.stayhomegallery.data.store.UnsplashStore
import non.shahad.stayhomegallery.utils.configs.UnsplashConfig
import non.shahad.stayhomegallery.utils.ext.timberD
import non.shahad.stayhomegallery.utils.prefs.SharedPrefHelper
import javax.inject.Inject
import javax.inject.Named

class HomeViewModel @Inject constructor(
    private val repo: HomeRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    val unsplashResponse = MutableLiveData<List<Post>>()

    val freshResponse = MutableLiveData<List<Post>>()

    val error = MutableLiveData<String>()

    private val errorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error.postValue(throwable.message)
        isLoading.value = false
    }

    fun fetchUnsplashNewsByOrder(page: Int){
        isLoading.value = true
        viewModelScope.launch (errorExceptionHandler){
            withContext(Dispatchers.IO){
                val result = repo.fetchUnsplashImagesByOrder(page)
                unsplashResponse.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun fetchFresh(page: Int){
        isLoading.value = true
        viewModelScope.launch (errorExceptionHandler){
            withContext(Dispatchers.IO){
                val result = repo.fetchFreshUnsplashImages(page)
                freshResponse.postValue(result)
                isLoading.postValue(false)
            }
        }
    }


    fun putOrder(orderBy: String){
        repo.putOrder(orderBy)
    }



}