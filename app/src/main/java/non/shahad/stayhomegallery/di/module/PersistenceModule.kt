package non.shahad.stayhomegallery.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import non.shahad.stayhomegallery.data.db.dao.PostDao
import non.shahad.stayhomegallery.data.db.database.LouvreDatabase
import non.shahad.stayhomegallery.utils.constants.Persistence
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
}