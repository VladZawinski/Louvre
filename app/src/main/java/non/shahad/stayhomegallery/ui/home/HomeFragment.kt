package non.shahad.stayhomegallery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dropbox.android.external.store4.ExperimentalStoreApi
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_header.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.fragment.BaseFragment
import non.shahad.stayhomegallery.data.local.mapper.mapToBookmark
import non.shahad.stayhomegallery.di.ViewModelFactory
import non.shahad.stayhomegallery.ui.bottomsheets.SortByBottomSheetFragment
import non.shahad.stayhomegallery.ui.recyclerviewutils.RecyclerViewPaginator
import non.shahad.stayhomegallery.utils.constants.BottomNavigation
import non.shahad.stayhomegallery.utils.constants.SortByReq
import non.shahad.stayhomegallery.utils.ext.switchToDetail
import non.shahad.stayhomegallery.utils.ext.timberD
import non.shahad.stayhomegallery.utils.ext.toast
import non.shahad.stayhomegallery.utils.network.Status
import java.io.File
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    private val viewModel by injectViewModels<HomeViewModel>()

    private lateinit var delegate : HomeAdapterDelegation

    private lateinit var paginator : RecyclerViewPaginator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @ExperimentalStoreApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initiateRecyclerView()
        swipeToRefresh()
        handleBottomSheetEvents()

    }

    private fun handleBottomSheetEvents(){
        include.sortedByBtn.setOnClickListener {
            val bottomSheet = SortByBottomSheetFragment{
                when(it){
                    SortByReq.OLDEST -> {
                        viewModel.putOrder("oldest")
                        viewModel.fetchFresh(1)
                    }
                    SortByReq.TRENDING -> {
                        viewModel.putOrder("trending")
                        viewModel.fetchFresh(1)
                    }
                    SortByReq.LATEST -> {
                        viewModel.putOrder("latest")
                        viewModel.fetchFresh(1)
                    }
                }
            }
            bottomSheet.setTargetFragment(this,101)
            bottomSheet.show(fragmentManager!!,HomeFragment::class.java.toString())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.fetchUnsplashByOrder(1)

        viewModel.unsplashResponse.observe(viewLifecycleOwner, Observer {
            delegate.insertItems(lifecycleScope,it)
        })

        viewModel.freshResponse.observe(viewLifecycleOwner, Observer {
            delegate.items = it
            swipeRefresh.isRefreshing = false
            paginator.resetCurrentPage()
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            timberD("HomeFragment_","$it")
        })

    }

    @ExperimentalStoreApi
    private fun swipeToRefresh(){
        swipeRefresh.setOnRefreshListener { viewModel.fetchFresh(1) }

    }


    private fun initiateRecyclerView(){
        homeRecyclerView.apply {
            adapter = delegate

            paginator = RecyclerViewPaginator(
                this,
                isLoading = {viewModel.isLoading},
                loadMore = {
                    viewModel.fetchUnsplashByOrder(it.toLong())
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
            onRootClick = { post,view ->
                requireContext().switchToDetail(activity as AppCompatActivity,post = post,sharedElement = view)
            }
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}