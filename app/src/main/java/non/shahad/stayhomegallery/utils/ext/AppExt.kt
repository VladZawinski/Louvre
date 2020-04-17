package non.shahad.stayhomegallery.utils.ext

import android.content.Context
import android.widget.Toast
import timber.log.Timber

fun timberD(tag : String,msg : String){
    Timber.tag(tag).d(msg)
}

fun timberE(tag : String,msg : String){
    Timber.tag(tag).e(msg)
}

fun Context.toast(msg : String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}