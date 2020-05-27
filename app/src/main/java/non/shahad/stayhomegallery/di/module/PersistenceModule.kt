package non.shahad.stayhomegallery.di.module

import android.content.Context
import androidx.room.Room

import dagger.Module
import dagger.Provides
import non.shahad.stayhomegallery.data.db.dao.BookmarkDao
import non.shahad.stayhomegallery.data.db.dao.CollectionDao
import non.shahad.stayhomegallery.data.db.dao.PostDao
import non.shahad.stayhomegallery.data.db.database.LouvreDatabase
import non.shahad.stayhomegallery.utils.constants.Persistence
import non.shahad.stayhomegallery.utils.prefs.SharedPrefHelper
import java.io.File
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideLouvreDatabase(context: Context) : LouvreDatabase {
        return Room.databaseBuilder(
            context,
            LouvreDatabase::class.java,
            Persistence.DB_NAME
        ).fallbackToDestructiveMigration()
         .build()
    }

    @Provides
    @Singleton
    fun providePostDao(db : LouvreDatabase) : PostDao{
        return db.postDao()
    }

    @Provides
    @Singleton
    fun provideBookmarkDao(db : LouvreDatabase) : BookmarkDao{
        return db.bookmarkDao()
    }

    @Provides
    @Singleton
    fun provideCollectionDao(db : LouvreDatabase) : CollectionDao{
        return db.collectionDao()
    }

    @Provides
    @Singleton
    fun providePrefHelper(ctx : Context) : SharedPrefHelper{
        return SharedPrefHelper(ctx)
    }

    @Provides
    @Singleton
    fun provideCacheDir(context: Context) : File{
        return context.cacheDir
    }


}