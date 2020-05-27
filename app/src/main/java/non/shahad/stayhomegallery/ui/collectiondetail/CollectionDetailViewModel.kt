package non.shahad.stayhomegallery.ui.collectiondetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.fresh
import com.dropbox.android.external.store4.get
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.utils.configs.CollectionDetailConfig
import non.shahad.stayhomegallery.utils.ext.timberD
import javax.inject.Inject
import javax.inject.Named

class CollectionDetailViewModel @Inject constructor(
    @Named("collectionDetailStore")
    private val store: Store<Pair<Long, CollectionDetailConfig>, List<Post>>
) : ViewModel() {

    var isLoading = false
    var isOnLast = false

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        timberD("CollectionDetail_","${throwable.message}")
    }

    val photos = MutableLiveData<List<Post>>()

    fun fetchPhotosOfCollection(id: Long,page: Long){
        isLoading = true
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO){
                val config = Pair<Long,CollectionDetailConfig>(page, CollectionDetailConfig(id))

                val cache = store.get(config)
                photos.postValue(cache)

                if (cache.isNullOrEmpty()){
                    val remote = store.fresh(config)
                    photos.postValue(remote)

                    if (remote.isNullOrEmpty()) isOnLast = true
                }

                isLoading = false
            }
        }
    }
}