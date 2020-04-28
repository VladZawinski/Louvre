package non.shahad.stayhomegallery.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.repository.BookmarkRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
): ViewModel() {

    fun insertIntoBookmark(bookmark: Bookmark){
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                bookmarkRepository.insertIntoBookmark(bookmark)
            }
        }

    }
}