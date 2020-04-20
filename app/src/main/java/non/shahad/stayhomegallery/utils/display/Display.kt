package non.shahad.stayhomegallery.utils.display

import android.content.Context
import android.util.DisplayMetrics

class Display (private val context: Context){

    fun covertDpToPx(dp : Int) : Int{
        return dp * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
    }

    fun convertPxToDp(px : Int) : Int{
        return px * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
    }
}