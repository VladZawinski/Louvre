package non.shahad.stayhomegallery.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.repository.BookmarkRepository
import non.shahad.stayhomegallery.utils.ext.timberD
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
): ViewModel() {

    val isAlreadyBookmaked = MutableLiveData<Boolean>()

    /**
     * false for remove
     * true for insert
     */
    val transactionStatus =  MutableLiveData<Boolean>()

    fun insertIntoBookmark(bookmark: Bookmark){
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                try {
                    bookmarkRepository.insertIntoBookmark(bookmark)
                    transactionStatus.postValue(true)
                }catch (e : Throwable){

                }
            }
        }
    }

    fun removeFromBookmark(postId : String){
        viewModelScope.launch {
            try {
                bookmarkRepository.removeFromBookmark(postId)
                transactionStatus.postValue(false)
            }catch (e : Throwable){

            }

        }
    }

    fun considerBookmark(postId : String){
        viewModelScope.launch {
            try {
                isAlreadyBookmaked.value = bookmarkRepository.isAlreadyBookmark(postId)
            }catch (e : Throwable){

            }
        }
    }
}