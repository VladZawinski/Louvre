package non.shahad.stayhomegallery.utils.prefs

import android.content.Context
import android.content.SharedPreferences
import non.shahad.stayhomegallery.utils.constants.Persistence

class SharedPrefHelper(
    val context: Context
) {
    private val sharedPreferences = context.getSharedPreferences(Persistence.PREF_NAME,Context.MODE_PRIVATE)
    private lateinit var editor : SharedPreferences.Editor

    fun putOrder(orderBy: String){
        edit()
        editor.putString(Persistence.ORDER_KEY,orderBy)
        editor.apply()
    }

    fun getOrder() : String = sharedPreferences.getString(Persistence.ORDER_KEY,"latest")!!

    private fun edit(){
        editor = sharedPreferences.edit()
    }
}