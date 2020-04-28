package non.shahad.stayhomegallery.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.repository.BookmarkRepository
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val bookmarkRepo : BookmarkRepository
) : ViewModel (){

    var bookmarks = MutableLiveData<List<Bookmark>>()

    fun fetchAllBookmarks() {
        viewModelScope.launch {
            val result = bookmarkRepo.getAllBookmarks()
            bookmarks.postValue(result)
        }
    }
}