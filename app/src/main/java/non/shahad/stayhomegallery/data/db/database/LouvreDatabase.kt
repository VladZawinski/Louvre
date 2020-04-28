package non.shahad.stayhomegallery.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import non.shahad.stayhomegallery.data.db.converters.ListPreviewPhotoConverter
import non.shahad.stayhomegallery.data.db.converters.ListStringConverter
import non.shahad.stayhomegallery.data.db.dao.BookmarkDao
import non.shahad.stayhomegallery.data.db.dao.CollectionDao
import non.shahad.stayhomegallery.data.db.dao.PostDao
import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.db.entity.Post

@Database(entities = [
    Post::class,Bookmark::class
],version = 1,exportSchema = false)
@TypeConverters(ListStringConverter::class,ListPreviewPhotoConverter::class)
abstract class LouvreDatabase : RoomDatabase(){
    abstract fun postDao() : PostDao

    abstract fun bookmarkDao() : BookmarkDao

    abstract fun collectionDao() : CollectionDao
}