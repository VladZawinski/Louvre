package non.shahad.stayhomegallery.ui.collections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.fresh
import com.dropbox.android.external.store4.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.data.repository.CollectionRepository
import javax.inject.Inject

class CollectionsViewModel @Inject constructor(
    private val repo : CollectionRepository
) : ViewModel(){

    val collectionsResponse = MutableLiveData<List<Collection>>()
    val error = MutableLiveData<String>()
    var isLoading = false

    fun fetchCollections(page : Long){
        viewModelScope.launch {
            isLoading = true
            try {
                val cached = withContext(Dispatchers.IO){
                    repo.collectionStore(page).get(page)
                }

                if (cached.isNotEmpty()) {
                    collectionsResponse.postValue(cached)
                    isLoading = false
                    return@launch
                }

                val fresh = withContext(Dispatchers.IO){
                    repo.collectionStore(page).fresh(page)
                }
                collectionsResponse.postValue(fresh)
                isLoading = false

            }catch (e : Throwable){
                error.postValue(e.message)
            }
        }
    }

}