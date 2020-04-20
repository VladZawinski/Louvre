package non.shahad.stayhomegallery.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.databinding.PostGridItemBinding
import non.shahad.stayhomegallery.ui.viewholder.PostViewHolder
import non.shahad.stayhomegallery.utils.ext.bindedView

class PostAdapterDelegate() : AdapterDelegate<List<Post>>() {

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



}