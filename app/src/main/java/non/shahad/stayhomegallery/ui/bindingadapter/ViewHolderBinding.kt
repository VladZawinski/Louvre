package non.shahad.stayhomegallery.ui.bindingadapter

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import non.shahad.stayhomegallery.data.db.entity.PreviewPhoto

@BindingAdapter("bindImageCount")
fun bindImageCount(textView: AppCompatTextView,count : Int){
    textView.text = "$count images"
}

@BindingAdapter("bindPreviewImage","palette")
fun bindImageWithUrl(imageView: ImageView, preview : PreviewPhoto,palette : View){
    val _url = preview.urls.regular

    Glide.with(imageView.context)
        .load(_url)
        .listener(
            GlidePalette.with(_url)
            .use(BitmapPalette.Profile.VIBRANT)
            .intoBackground(palette)
            .crossfade(true))
        .into(imageView)
}