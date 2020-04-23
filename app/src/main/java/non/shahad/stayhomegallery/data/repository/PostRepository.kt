package non.shahad.stayhomegallery.data.repository

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreBuilder
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



}