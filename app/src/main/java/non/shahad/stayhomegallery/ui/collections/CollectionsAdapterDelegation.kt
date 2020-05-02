package non.shahad.stayhomegallery.ui.collections

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.utils.diffcallbacks.CollectionDiffCallback

class CollectionsAdapterDelegation(
    onRootClick : (Collection) -> Unit
) : AsyncListDifferDelegationAdapter<Collection>(CollectionDiffCallback) {

    init {
        delegatesManager
            .addDelegate(CollectionsAdapterDelegate(onRootClick))
    }

    /**
     * Enhance performance while sorting list
     * you can use suspend tho(but buggy)
     */
    fun insertItems(coroutineScope: CoroutineScope, collections : List<Collection>){
        coroutineScope.launch {

            val result = withContext(Dispatchers.Default){
                val local = mutableListOf<Collection>()
                local.addAll(items)
                local.addAll(collections)
                return@withContext local
            }

            items = result
        }

    }

}