package non.shahad.stayhomegallery.utils.ext

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.ui.detail.DetailActivity
import non.shahad.stayhomegallery.ui.search.SearchActivity
import non.shahad.stayhomegallery.utils.constants.Bundles
import non.shahad.stayhomegallery.utils.constants.ViewConstants

fun Context.switchToDetail(activity: AppCompatActivity,post : Post,sharedElement : View? = null){
    val intent = Intent(this,DetailActivity::class.java).also {
        it.putExtra(Bundles.POST_EXTRA,post)
    }

    val options = ActivityOptionsCompat
        .makeSceneTransitionAnimation(activity,sharedElement!!,ViewConstants.PHOTO_SHARED_ELEMENT_NAME)

    this.startActivity(intent,options.toBundle())
}

fun Context.switchToSearchActivity(activity: AppCompatActivity,sharedElement: View? = null){
    val options = ActivityOptionsCompat
        .makeSceneTransitionAnimation(activity,sharedElement!!,ViewConstants.SEARCH_SHARED_ELEMENT_NAME)

    this.startActivity(Intent(this,SearchActivity::class.java),options.toBundle())
}