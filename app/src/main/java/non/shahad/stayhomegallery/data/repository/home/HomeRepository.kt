package non.shahad.stayhomegallery.data.repository.home

import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.utils.configs.UnsplashConfig

interface HomeRepository {
    suspend fun fetchUnsplashImagesByOrder(page: Int): List<Post>
    suspend fun fetchFreshUnsplashImages(page: Int): List<Post>
    fun getChoseOrder(): String
    fun putOrder(order: String)
    fun createConfig(page: Int): Pair<Int,UnsplashConfig>

}