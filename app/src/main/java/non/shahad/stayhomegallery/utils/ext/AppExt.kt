package non.shahad.stayhomegallery.utils.ext

import android.content.Context
import android.content.Intent
import android.widget.Toast
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.ui.collectiondetail.CollectionDetailActivity
import non.shahad.stayhomegallery.utils.constants.ExtraKeys
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

fun Context.switchToCollectionDetail(detail : Collection){
    Intent(this,CollectionDetailActivity::class.java).also {
        it.putExtra(ExtraKeys.COLLECTION_ID_KEY,detail)
        startActivity(it)
    }
}