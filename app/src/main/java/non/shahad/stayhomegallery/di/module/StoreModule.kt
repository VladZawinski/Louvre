package non.shahad.stayhomegallery.di.module

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import non.shahad.stayhomegallery.data.db.dao.CollectionDao
import non.shahad.stayhomegallery.data.db.dao.PostDao
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.remote.LouvreAPI
import non.shahad.stayhomegallery.data.store.UnsplashStore
import non.shahad.stayhomegallery.utils.configs.CollectionDetailConfig
import non.shahad.stayhomegallery.utils.configs.PexelsConfig
import non.shahad.stayhomegallery.utils.configs.UnsplashConfig
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class StoreModule {

    @ExperimentalCoroutinesApi
    @FlowPreview
    @Provides
    @Named("unsplashPostStore")
    @Singleton
    fun provideUnsplashStore(db: PostDao, remote: LouvreAPI) : UnsplashStore {
        return StoreBuilder
            .fromNonFlow<Pair<Int,UnsplashConfig>,List<Post>> { (page, config) ->
                remote.getUnsplashImages(page, config.orderBy).posts
            }
            .cachePolicy(
                MemoryPolicy.builder()
                    .setExpireAfterAccess(5) // or setExpireAfterWrite(10)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)
                    .build()
            )
            .build()
    }


    @Provides
    @Named("searchStore")
    @Singleton
    @ExperimentalCoroutinesApi
    @FlowPreview
    fun provideSearchPexelsStore(db: PostDao, remote: LouvreAPI) : Store<Pair<Long,PexelsConfig>,List<Post>>{
        return StoreBuilder
            .fromNonFlow<Pair<Long,PexelsConfig>,List<Post>> {(page , config) ->
                remote.search(config.query,page).posts
            }
            .cachePolicy(
                MemoryPolicy.builder()
                    .setExpireAfterAccess(5) // or setExpireAfterWrite(10)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)
                    .build()
            )
            .build()
    }

    @Provides
    @Named("collectionDetailStore")
    @Singleton
    fun provideCollectionDetailStore(db: PostDao,remote: LouvreAPI) : Store<Pair<Long,CollectionDetailConfig>,List<Post>>{
        return StoreBuilder
            .fromNonFlow<Pair<Long,CollectionDetailConfig>,List<Post>>{(page, config) ->
                remote.getCollectionDetail(config.id,page).photos
            }
            .cachePolicy(
                MemoryPolicy.builder()
                    .setExpireAfterAccess(30) // or setExpireAfterWrite(10)
                    .setExpireAfterTimeUnit(TimeUnit.MINUTES)
                    .build()
            )
            .build()

    }


    @Provides
    @Named("exploreCollectionStore")
    @Singleton
    fun provideExploreCollectionStore(api: LouvreAPI) : Store<Long,List<Collection>>{
        return StoreBuilder
            .fromNonFlow{page : Long-> api.getCollections(page).collections
        }.cachePolicy(
            MemoryPolicy.builder()
                .setExpireAfterAccess(30)// or setExpireAfterWrite(10)
                .setExpireAfterTimeUnit(TimeUnit.MINUTES)
                .build()
        ).build()

    }





}