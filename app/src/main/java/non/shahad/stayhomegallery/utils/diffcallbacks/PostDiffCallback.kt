package non.shahad.stayhomegallery.utils.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import non.shahad.stayhomegallery.data.db.entity.Post

object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.originalId == newItem.originalId
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.color == newItem.color

    }
}