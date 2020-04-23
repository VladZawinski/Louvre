package non.shahad.stayhomegallery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.fragment.BaseFragment
import non.shahad.stayhomegallery.data.local.mapper.mapToBookmark
import non.shahad.stayhomegallery.di.ViewModelFactory
import non.shahad.stayhomegallery.ui.recyclerviewutils.RecyclerViewPaginator
import non.shahad.stayhomegallery.utils.ext.switchToDetail
import non.shahad.stayhomegallery.utils.ext.timberD
import non.shahad.stayhomegallery.utils.ext.toast
import non.shahad.stayhomegallery.utils.network.Status
import java.io.File
import javax.inject.Inject


class HomeFragment : BaseFragment() {

    private val viewModel by injectViewModels<HomeViewModel>()

    private lateinit var delegate : HomeAdapterDelegation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initiateRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.unsplashResponse.observe(viewLifecycleOwner, Observer {
            when(it.status){

                Status.SUCCESS -> {
                    lifecycleScope.launch {
                        delay(1000)
                        delegate.insertItems(lifecycleScope,it.data!!)
                    }

                }
                Status.ERROR -> requireContext().toast(it.message!!)
            }
        })

        viewModel.fetchUnsplashStore(1)

    }

    private fun initiateRecyclerView(){
        homeRecyclerView.apply {
            adapter = delegate

            RecyclerViewPaginator(
                this,
                isLoading = {viewModel.isLoading},
                loadMore = {
                    viewModel.fetchUnsplashStore(it.toLong())
                },
                onLast = {false}
            ).apply {
                threshold = 1
                currentPage = 1
            }
        }
    }

    private fun initAdapter(){
        delegate = HomeAdapterDelegation(
            onFavoriteClick = {post, b ->
                viewModel.insertIntoBookmark(post.mapToBookmark())
            },
            onRootClick = {
                requireContext().switchToDetail()
            }
        )

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}