package non.shahad.stayhomegallery.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import non.shahad.stayhomegallery.data.db.dao.CollectionDao
import non.shahad.stayhomegallery.data.model.CollectionResponse
import non.shahad.stayhomegallery.data.remote.LouvreAPI
import javax.inject.Inject

class CollectionRepository @Inject constructor(
    private val api : LouvreAPI,
    private val dao : CollectionDao
) {

    suspend fun collectionStore(page : Long) : CollectionResponse = withContext(Dispatchers.IO){
        return@withContext api.getCollections(page)
    }

}