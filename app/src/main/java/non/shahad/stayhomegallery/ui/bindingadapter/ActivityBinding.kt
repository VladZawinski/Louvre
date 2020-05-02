package non.shahad.stayhomegallery.ui.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("bindImageWithUrl")
fun bindImageWithUrl(imageView: ImageView,url : String){
    val _url = url
    Glide.with(imageView.context)
        .load(_url)
        .into(imageView)
}

@BindingAdapter("bindCircleImageView")
fun bindCircleImageView(imageView: CircleImageView,url : String){
    val _url = url
    Glide.with(imageView.context)
        .load(_url)
        .into(imageView)
}

