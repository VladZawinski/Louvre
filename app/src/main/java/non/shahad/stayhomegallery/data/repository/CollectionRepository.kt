package non.shahad.stayhomegallery.data.repository

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.dao.CollectionDao
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.data.model.CollectionResponse
import non.shahad.stayhomegallery.data.remote.LouvreAPI
import non.shahad.stayhomegallery.utils.ext.timberD
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

class CollectionRepository @Inject constructor(
    private val api : LouvreAPI,
    private val dao : CollectionDao
) {

    suspend fun fetchCollectionForExplore() : CollectionResponse = withContext(Dispatchers.IO){
        timberD("Collections_","${Math.random()}")
        return@withContext api.getCollections(1)
    }

    suspend fun collectionStore(page: Long) : Store<Long,List<Collection>> =
        StoreBuilder.fromNonFlow{key : Long->
            api.getCollections(page).collections
        }.cachePolicy(
            MemoryPolicy.builder()
                .setMemorySize(10)
                .setExpireAfterAccess(5)// or setExpireAfterWrite(10)
                .setExpireAfterTimeUnit(TimeUnit.SECONDS)
                .build()
        ).persister(
            reader = dao::getCollectionsByPage,
            writer = dao::insertByPage,
            delete = dao::deleteCollectionsByPage,
            deleteAll = dao::deleteAll
        ).build()


}