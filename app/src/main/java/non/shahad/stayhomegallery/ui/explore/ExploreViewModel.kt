package non.shahad.stayhomegallery.ui.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.data.repository.CollectionRepository
import non.shahad.stayhomegallery.utils.ext.timberE
import javax.inject.Inject

class ExploreViewModel @Inject constructor(
    private val repo : CollectionRepository
) : ViewModel(){

    val collectionResponse = MutableLiveData<List<Collection>>()

    fun fetchCollection(page : Long) {
        viewModelScope.launch {
            try {
                val result = repo.collectionStore(page)
                collectionResponse.value = result.collections
            }catch (e : Throwable){
                timberE("Explore_","$e")
            }
        }

    }
}