package non.shahad.stayhomegallery.ui.explore

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
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.utils.ext.timberE
import javax.inject.Inject
import javax.inject.Named

class ExploreViewModel @Inject constructor(
    @Named("exploreCollectionStore")
    private val store: Store<Long, List<Collection>>
) : ViewModel(){

    val collectionResponse = MutableLiveData<List<Collection>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        timberE("Explore_","${throwable.message}")
    }

    fun fetchCollection(page: Long) {
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO){
                collectionResponse.postValue(store.get(page))
            }
        }

    }
}