package non.shahad.stayhomegallery.ui.collectiondetail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.databinding.PostGridItemBinding
import non.shahad.stayhomegallery.ui.favorite.FavoriteAdapterDelegate
import non.shahad.stayhomegallery.utils.ext.bindedView
import non.shahad.stayhomegallery.utils.ext.gone
import non.shahad.stayhomegallery.utils.ext.visible

class CollectionDetailAdapterDelegate(
    val onRootClick : (Post, View) -> Unit
) : AdapterDelegate<List<Post>>(){

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CollectionPostViewHolder(bindedView(parent, R.layout.post_grid_item) as PostGridItemBinding)
    }

    override fun isForViewType(items: List<Post>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: List<Post>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as CollectionPostViewHolder).bind(items[position])
    }

    /**
     * Will use External class instead of Inner to avoid boilerplate
     */
    inner class CollectionPostViewHolder(val binding : PostGridItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post : Post){

            binding.removeBtn.gone()

            binding.root.setOnClickListener {
                onRootClick(post,binding.postImage)
            }

            Glide
                .with(binding.root.context)
                .load(post.img.low)
                .override(1080,720)
                .into(binding.postImage)
        }
    }
}