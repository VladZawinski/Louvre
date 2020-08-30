package non.shahad.stayhomegallery.data.store

import com.dropbox.android.external.store4.Store
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.utils.configs.UnsplashConfig

typealias UnsplashStore = Store<Pair<Int, UnsplashConfig>, List<Post>>