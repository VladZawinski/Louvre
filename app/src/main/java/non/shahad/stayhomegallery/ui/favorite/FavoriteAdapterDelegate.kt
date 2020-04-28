package non.shahad.stayhomegallery.ui.favorite

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.databinding.PostGridItemBinding
import non.shahad.stayhomegallery.utils.ext.bindedView
import non.shahad.stayhomegallery.utils.ext.visible

class FavoriteAdapterDelegate (
    val onFavoriteClick : (Bookmark,Boolean) -> Unit
): AdapterDelegate<List<Bookmark>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PostViewHolder(bindedView(parent, R.layout.post_grid_item) as PostGridItemBinding)
    }

    override fun isForViewType(items: List<Bookmark>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: List<Bookmark>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as PostViewHolder).bind(items[position])
    }

    /**
     * Will use External class instead of Inner to avoid boilerplate
     */
    inner class PostViewHolder(val binding : PostGridItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post : Bookmark){

            binding.removeBtn.visible()

            Glide
                .with(binding.root.context)
                .load(post.img.low)
                .override(1080,720)
                .into(binding.postImage)
        }
    }


}