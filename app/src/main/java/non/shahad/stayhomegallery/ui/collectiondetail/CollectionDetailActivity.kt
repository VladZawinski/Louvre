package non.shahad.stayhomegallery.ui.collectiondetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_collection_detail.*
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.activity.BaseActivity
import non.shahad.stayhomegallery.data.db.entity.Collection
import non.shahad.stayhomegallery.ui.recyclerviewutils.RecyclerViewPaginator
import non.shahad.stayhomegallery.utils.constants.ExtraKeys
import non.shahad.stayhomegallery.utils.ext.switchToDetail
import non.shahad.stayhomegallery.utils.ext.timberD

class CollectionDetailActivity : BaseActivity() {

    private val vm by injectViewModels<CollectionDetailViewModel>()

    private lateinit var delegate : CollectionDetailAdapterDelegation

    private lateinit var paginator : RecyclerViewPaginator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection_detail)
        wantToMakeStatusBarWhite(true)
        adapterThings()

        intent?.extras?.getParcelable<Collection>(ExtraKeys.COLLECTION_ID_KEY)?.also {
            toolBarThings(it.title)
            recyclerViewthings(it.id.toLong())
            vm.fetchPhotosOfCollection(it.id.toLong(),1)
        }

        vm.photos.observe(this, Observer {
            delegate.insertItems(lifecycleScope,it)
        })
    }

    private fun adapterThings(){
        delegate = CollectionDetailAdapterDelegation{p , v ->
            switchToDetail(this, p,v)
        }
    }

    private fun recyclerViewthings(collectionID : Long){
        with(recyclerView){
            recyclerView.adapter = delegate

            paginator = RecyclerViewPaginator(
                this,
                isLoading = {vm.isLoading},
                loadMore = {
                    vm.fetchPhotosOfCollection(collectionID,it.toLong())
                },
                onLast = {vm.isOnLast}
            ).apply {
                threshold = 1
                currentPage = 1
            }
        }
    }

    private fun toolBarThings(title: String){
        toolbar.apply {
            toolBarTxt.text = title
            setSupportActionBar(this)
            supportActionBar?.title =""
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}