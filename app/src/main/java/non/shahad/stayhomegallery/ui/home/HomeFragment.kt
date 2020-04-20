package non.shahad.stayhomegallery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.android.synthetic.main.fragment_home.*
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.fragment.BaseFragment
import non.shahad.stayhomegallery.data.remote.LouvreAPI
import non.shahad.stayhomegallery.di.ViewModelFactory
import non.shahad.stayhomegallery.ui.recyclerviewutils.RecyclerViewPaginator
import non.shahad.stayhomegallery.utils.ext.timberD
import non.shahad.stayhomegallery.utils.ext.toast
import non.shahad.stayhomegallery.utils.network.Status
import javax.inject.Inject


class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel : HomeViewModel

    private val delegate by lazy {HomeAdapterDelegation()}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateRecyclerView()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchUnsplash(1)

        viewModel.unsplashResponse.observe(this, Observer {
            when(it){
                is NetworkResponse.Success -> {
                    delegate.insertItems(it.body.posts)
                }
                is NetworkResponse.NetworkError -> {

                }
                is NetworkResponse.ServerError -> {}
            }
        })
    }

    private fun initiateRecyclerView(){
        homeRecyclerView.apply {
            adapter = delegate

            RecyclerViewPaginator(
                this,
                isLoading = {viewModel.isLoading},
                loadMore = {viewModel.fetchUnsplash(it.toLong())},
                onLast = {false}
            ).apply {
                threshold = 3
                currentPage = 1
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}