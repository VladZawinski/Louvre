package non.shahad.stayhomegallery.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.databinding.PostGridItemBinding
import non.shahad.stayhomegallery.utils.ext.bindedView
import non.shahad.stayhomegallery.utils.ext.visible

class PostAdapterDelegate(
    val onRootClick : (Post,View) -> Unit
) : AdapterDelegate<List<Post>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PostViewHolder(bindedView(parent,R.layout.post_grid_item) as PostGridItemBinding)
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
        (holder as PostViewHolder).bind(items[position])
    }

   inner class PostViewHolder(val binding : PostGridItemBinding) : RecyclerView.ViewHolder(binding.root) {
       private val set = ConstraintSet()

        fun bind(post : Post){

            binding.mConstraintLayout.setOnClickListener {
                onRootClick(post,it)
            }

            Glide
                .with(binding.root.context)
                .load(post.img.low)
                .into(binding.postImage)

            val ratio = String.format("%d:%d",post.width,post.height)
            set.clone(binding.mConstraintLayout)
            set.setDimensionRatio(binding.postImage.id,ratio)
            set.applyTo(binding.mConstraintLayout)


        }
    }



}