package non.shahad.stayhomegallery.ui.explore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.R
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.databinding.ExploreCollectionItemBinding
import non.shahad.stayhomegallery.utils.ext.bindedView

class CollectionAdapterDelegate : AdapterDelegate<List<Collection>>(){

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CollectionItemViewHolder(
            bindedView(parent,non.shahad.stayhomegallery.R.layout.explore_collection_item)
            as ExploreCollectionItemBinding
        )
    }

    override fun isForViewType(items: List<Collection>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: List<Collection>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as CollectionItemViewHolder).bind(items[position])
    }

    inner class CollectionItemViewHolder(
        val binding : ExploreCollectionItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(collection: Collection){
            binding.preview = collection
            binding.palette = binding.overlayView
        }
    }

}