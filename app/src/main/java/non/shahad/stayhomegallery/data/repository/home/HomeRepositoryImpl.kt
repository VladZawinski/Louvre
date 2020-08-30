package non.shahad.stayhomegallery.data.repository.home

import com.dropbox.android.external.store4.fresh
import com.dropbox.android.external.store4.get
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.store.UnsplashStore
import non.shahad.stayhomegallery.utils.configs.UnsplashConfig
import non.shahad.stayhomegallery.utils.prefs.SharedPrefHelper
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val pref: SharedPrefHelper,
    private val store: UnsplashStore
): HomeRepository {

    override suspend fun fetchUnsplashImagesByOrder(page: Int): List<Post> {
        return store.get(createConfig(page))
    }

    override suspend fun fetchFreshUnsplashImages(page: Int): List<Post> {
        return store.fresh(createConfig(page))
    }

    override fun getChoseOrder(): String = pref.getOrder()

    override fun putOrder(order: String) {
        pref.putOrder(order)
    }

    override fun createConfig(page: Int): Pair<Int,UnsplashConfig> {
        return Pair(page, UnsplashConfig(getChoseOrder()))
    }

}