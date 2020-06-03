package non.shahad.stayhomegallery.ui.collections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreRequest
import com.dropbox.android.external.store4.fresh
import com.dropbox.android.external.store4.get
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.entity.Collection
import javax.inject.Inject
import javax.inject.Named

class CollectionsViewModel @Inject constructor(
    @Named("exploreCollectionStore")
    private val store: Store<Long, List<Collection>>
) : ViewModel(){

    val collectionsResponse = MutableLiveData<List<Collection>>()
    val error = MutableLiveData<String>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error.postValue(throwable.message)
    }

    var isLoading = false

    fun fetchCollections(page : Long){
        viewModelScope.launch(exceptionHandler) {
            isLoading = true
            withContext(Dispatchers.IO){
                collectionsResponse.postValue(store.get(page))
                isLoading = false
            }
        }
    }

}