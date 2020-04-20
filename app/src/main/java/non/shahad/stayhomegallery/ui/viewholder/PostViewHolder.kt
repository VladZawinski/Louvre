package non.shahad.stayhomegallery.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.databinding.PostGridItemBinding

class PostViewHolder(val binding : PostGridItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post : Post){
        Glide
            .with(binding.root.context)
            .load(post.img.low)
            .override(1080,720)
            .into(binding.postImage)

    }
}