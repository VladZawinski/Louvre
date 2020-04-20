package non.shahad.stayhomegallery.data.db.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import non.shahad.stayhomegallery.data.db.converters.ListStringConverter
import non.shahad.stayhomegallery.data.db.dao.PostDao
import non.shahad.stayhomegallery.data.db.entity.Post

@Database(entities = [
    Post::class
],version = 2,exportSchema = false)
@TypeConverters(ListStringConverter::class)
abstract class LouvreDatabase : RoomDatabase(){
    abstract fun postDao() : PostDao
}