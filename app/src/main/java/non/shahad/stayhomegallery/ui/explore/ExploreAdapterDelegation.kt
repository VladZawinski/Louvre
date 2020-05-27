package non.shahad.stayhomegallery.ui.explore

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.utils.diffcallbacks.CollectionDiffCallback

class ExploreAdapterDelegation(
    private val onRootClick : (Collection) -> Unit
) : AsyncListDifferDelegationAdapter<Collection>(CollectionDiffCallback) {
    init {
        delegatesManager.addDelegate(CollectionAdapterDelegate(onRootClick))
    }
}