package non.shahad.stayhomegallery.ui.collections

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.databinding.CollectionsItemBinding
import non.shahad.stayhomegallery.utils.ext.bindedView

class CollectionsAdapterDelegate(
    val onRootClick : (Collection) -> Unit
) : AdapterDelegate<List<Collection>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CollectionsViewHolder(bindedView(parent,R.layout.collections_item) as CollectionsItemBinding)
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
        (holder as CollectionsViewHolder).bind(items[position])
    }

    inner class CollectionsViewHolder(private val binding : CollectionsItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(collection: Collection){
            binding.root.setOnClickListener {
                onRootClick(collection)
            }

            binding.preview = collection
        }

    }
}