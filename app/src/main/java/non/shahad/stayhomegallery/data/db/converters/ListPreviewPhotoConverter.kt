package non.shahad.stayhomegallery.data.db.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import non.shahad.stayhomegallery.data.db.entity.PreviewPhoto

@TypeConverters
open class ListPreviewPhotoConverter{

    @TypeConverter
    fun fromString(value : String) : List<PreviewPhoto>? {
        val listType = object : TypeToken<List<PreviewPhoto>>(){}.type
        return Gson().fromJson<List<PreviewPhoto>>(value, listType)
    }

    @TypeConverter
    fun fromList(value : List<PreviewPhoto>) : String?{
        return Gson().toJson(value)
    }
}