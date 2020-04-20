package non.shahad.stayhomegallery.ui.home

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.utils.diffcallbacks.PostDiffCallback

class HomeAdapterDelegation() : AsyncListDifferDelegationAdapter<Post>(PostDiffCallback){
    init {
        delegatesManager.addDelegate(PostAdapterDelegate())
    }

    fun insertItems(posts : List<Post>){
        val newList = ArrayList<Post>()
        newList.addAll(items)
        newList.addAll(posts)
        items = newList
    }
}