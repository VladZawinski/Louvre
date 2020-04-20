package non.shahad.stayhomegallery.data.db.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@TypeConverters
open class ListStringConverter{

    @TypeConverter
    fun fromString(value : String) : List<String>? {
        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson<List<String>>(value, listType)
    }

    @TypeConverter
    fun fromList(value : List<String>) : String?{
        return Gson().toJson(value)
    }
}