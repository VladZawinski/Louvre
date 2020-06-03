package non.shahad.stayhomegallery.ui.search

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
import non.shahad.stayhomegallery.utils.configs.PexelsConfig
import javax.inject.Inject
import javax.inject.Named

class SearchViewModel @Inject constructor(
    @Named("searchStore")
    val searchStore: Store<Pair<Long, PexelsConfig>,List<Post>>
) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->

    }

    val searchResult = MutableLiveData<List<Post>>()

    fun search(query: String,page: Long){
        viewModelScope.launch(errorHandler) {
            withContext(Dispatchers.IO){
                val config = Pair<Long,PexelsConfig>(page, PexelsConfig(query))

                searchResult.postValue(searchStore.get(config))
            }
        }
    }
}