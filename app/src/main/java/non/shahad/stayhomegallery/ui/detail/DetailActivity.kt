package non.shahad.stayhomegallery.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_topbar.view.*
import kotlinx.android.synthetic.main.user_bottom_sheet.*
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.activity.BaseActivity
import non.shahad.stayhomegallery.data.db.entity.Post
import non.shahad.stayhomegallery.data.local.mapper.mapToBookmark
import non.shahad.stayhomegallery.databinding.ActivityDetailBinding
import non.shahad.stayhomegallery.utils.constants.Bundles
import non.shahad.stayhomegallery.utils.ext.toast

class DetailActivity : BaseActivity(),View.OnClickListener {

    private lateinit var post: Post

    private val binding by binding<ActivityDetailBinding>(R.layout.activity_detail)

    private val viewModel by injectViewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        wantToMakeStatusBarWhite(true)
        setUpBottomSheet()
        post = intent.extras?.getParcelable<Post>(Bundles.POST_EXTRA)!!

        bindData()
        observerTransactionStatus()
        considerBookmarkOrNot()
        setOnClickListener()
    }

    private fun setOnClickListener(){
        binding.topbar.listener = this
    }

    private fun bindData(){
        binding.post = post
        binding.bottomseet.user = post.user
    }

    private fun observerTransactionStatus(){
        viewModel.transactionStatus.observe(this, Observer {
            when(it){
                true -> {
//                    toast("Bookmark Success")
                }
                false -> {
//                    toast("Remove from bookmark")
                }
            }
        })
    }


    private fun considerBookmarkOrNot(){
        viewModel.considerBookmark(postId = post.originalId)
        viewModel.isAlreadyBookmaked.observe(this, Observer {
            post.isBookmark = it
            if (it){
                onBookmark()
            }
        })
    }

    private fun onBookmark(){
        post.isBookmark = true
        binding.topbar.apply {
            this.favoriteBtn.setBackgroundColor(ContextCompat.getColor(this@DetailActivity,R.color.transparent_cyan))
//            this.favoriteBtn.backgroundTintList = ContextCompat
//                .getColorStateList(this@DetailActivity,R.color.transparent_cyan)
            this.favoriteIcon.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity,R.drawable.ic_heart_rounded))
        }
    }

    private fun onRemove(){
        post.isBookmark = false
        binding.topbar.apply {
            this.favoriteBtn.background = ContextCompat.getDrawable(this@DetailActivity,R.drawable.detail_btn_style)
            this.favoriteIcon.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity,R.drawable.ic_heart))
        }
    }


    private fun setUpBottomSheet(){
        val behaviour = BottomSheetBehavior.from(binding.bottomseet.root)

        behaviour.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // React to state change
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // React to dragging events
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.favoriteBtn -> {
                if (!post.isBookmark){
                    onBookmark()
                    viewModel.insertIntoBookmark(post.mapToBookmark())
                }else {
                    onRemove()
                    viewModel.removeFromBookmark(post.originalId)
                }
            }
            R.id.shareBtn -> {
                toast("Share")
            }
            R.id.backBtn -> {
                this.finish()
            }
        }

    }
}
