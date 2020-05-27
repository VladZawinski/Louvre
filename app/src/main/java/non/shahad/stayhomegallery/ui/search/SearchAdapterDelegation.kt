package non.shahad.stayhomegallery.ui.search

import android.view.View
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.ui.home.PostAdapterDelegate
import non.shahad.stayhomegallery.utils.diffcallbacks.PostDiffCallback

class SearchAdapterDelegation(
    onRootClick : (Post, View) -> Unit
) : AsyncListDifferDelegationAdapter<Post>(PostDiffCallback){

    init {
        delegatesManager.addDelegate(PostAdapterDelegate(onRootClick))
    }

    /**
     * Enhance performance while sorting list
     * you can use suspend tho(but buggy)
     */
    fun insertItems(coroutineScope: CoroutineScope, posts : List<Post>){
        coroutineScope.launch {

            val result = withContext(Dispatchers.Default){
                val local = mutableListOf<Post>()
                local.addAll(items)
                local.addAll(posts)
                return@withContext local
            }

            items = result
        }

    }

}