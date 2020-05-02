package non.shahad.stayhomegallery.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_favorite.*
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.fragment.BaseFragment
import non.shahad.stayhomegallery.data.local.mapper.mapToPost
import non.shahad.stayhomegallery.di.ViewModelFactory
import non.shahad.stayhomegallery.ui.home.HomeAdapterDelegation
import non.shahad.stayhomegallery.ui.home.HomeViewModel
import non.shahad.stayhomegallery.utils.ext.switchToDetail
import javax.inject.Inject


class FavoriteFragment : BaseFragment() {

    private val viewModel by injectViewModels<FavoriteViewModel>()

    private lateinit var delegate : FavoriteAdapterDelegation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initRecyclerView()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if(!hidden){
            viewModel.fetchAllBookmarks()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchAllBookmarks()

        viewModel.bookmarks.observe(viewLifecycleOwner, Observer {
            delegate.items = it
        })
    }

    private fun initAdapter(){
        delegate = FavoriteAdapterDelegation(
            onFavoriteClick =  { bookmark, isbookmark ->

        },
            onRootClick = {bookmark,view  ->
                context?.switchToDetail((context as AppCompatActivity),bookmark.mapToPost(),view)
            }
        )
    }

    private fun initRecyclerView(){
        favoriteRecyclerView.apply {
            this.adapter = delegate
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FavoriteFragment()
    }
}