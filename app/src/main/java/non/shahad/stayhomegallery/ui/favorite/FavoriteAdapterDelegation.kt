package non.shahad.stayhomegallery.ui.favorite

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.utils.diffcallbacks.PostDiffCallback

class FavoriteAdapterDelegation(
    onFavoriteClick : (Post,Boolean) -> Unit
) : AsyncListDifferDelegationAdapter<Post>(PostDiffCallback) {

    init {
        delegatesManager.addDelegate(FavoriteAdapterDelegate(onFavoriteClick))
    }

}