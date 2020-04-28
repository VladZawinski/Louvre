package non.shahad.stayhomegallery.utils.diffcallbacks

import androidx.recyclerview.widget.DiffUtil
import non.shahad.stayhomegallery.data.db.entity.Collection

object CollectionDiffCallback : DiffUtil.ItemCallback<Collection>(){
    override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
        return oldItem.title == newItem.title
    }

}