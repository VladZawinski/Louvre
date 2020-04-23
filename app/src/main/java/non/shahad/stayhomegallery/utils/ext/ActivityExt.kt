package non.shahad.stayhomegallery.utils.ext

import android.content.Context
import android.content.Intent
import non.shahad.stayhomegallery.ui.detail.DetailActivity

fun Context.switchToDetail(){
    this.startActivity(Intent(this,DetailActivity::class.java))
}