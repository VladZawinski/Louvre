package non.shahad.stayhomegallery.ui.favorite

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.utils.diffcallbacks.BookmarkDiffCallback
import non.shahad.stayhomegallery.utils.diffcallbacks.PostDiffCallback

class FavoriteAdapterDelegation(
    onFavoriteClick : (Bookmark,Boolean) -> Unit
) : AsyncListDifferDelegationAdapter<Bookmark>(BookmarkDiffCallback) {

    init {
        delegatesManager.addDelegate(FavoriteAdapterDelegate(onFavoriteClick))
    }

}