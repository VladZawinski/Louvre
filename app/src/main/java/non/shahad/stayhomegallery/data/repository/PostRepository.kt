package non.shahad.stayhomegallery.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dropbox.android.external.store4.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.dao.PostDao
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.model.UnsplashResponse
import non.shahad.stayhomegallery.data.remote.LouvreAPI
import java.util.concurrent.TimeUnit

import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api : LouvreAPI,
    private val dao : PostDao
) {

    suspend fun fetchUnsplash(page : Long) : Store<Long,List<Post>>{
        return StoreBuilder.fromNonFlow {key : Long ->
            api.getUnsplashImages(page).posts
        }.cachePolicy(
            MemoryPolicy.builder()
                .setMemorySize(10)
                .setExpireAfterAccess(5)// or setExpireAfterWrite(10)
                .setExpireAfterTimeUnit(TimeUnit.SECONDS)
                .build()
        )
        .persister(
            reader = dao::getPostsByPage,
            writer = dao::insertByPage,
            delete = dao::deletePostsByPage,
            deleteAll = dao::deleteAll
        ).build()
    }

    @ExperimentalStoreApi
    suspend fun deleteAllCache() = fetchUnsplash(1).clearAll()

    suspend fun refresh() : List<Post> = withContext(Dispatchers.IO){
        // Delete all cache
//        dao.deleteAllExcludeBookmark()

        return@withContext fetchUnsplash(1).fresh(1)
    }



}