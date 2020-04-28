package non.shahad.stayhomegallery.utils.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import non.shahad.stayhomegallery.data.db.entity.Bookmark
import non.shahad.stayhomegallery.data.db.entity.Post

object BookmarkDiffCallback : DiffUtil.ItemCallback<Bookmark>() {

    override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
        return oldItem.originalId == newItem.originalId
    }

    override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
        return oldItem.user.id == newItem.user.id && oldItem.img.low == newItem.img.low
    }
}