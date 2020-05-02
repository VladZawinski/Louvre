package non.shahad.stayhomegallery.ui.favorite

import android.view.View
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.utils.diffcallbacks.BookmarkDiffCallback
import non.shahad.stayhomegallery.utils.diffcallbacks.PostDiffCallback

class FavoriteAdapterDelegation(
    onRootClick : (Bookmark,View) -> Unit,
    onFavoriteClick : (Bookmark,Boolean) -> Unit
) : AsyncListDifferDelegationAdapter<Bookmark>(BookmarkDiffCallback) {

    init {
        delegatesManager.addDelegate(FavoriteAdapterDelegate(onRootClick,onFavoriteClick))
    }

}