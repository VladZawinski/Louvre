package non.shahad.stayhomegallery.ui.collections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_collections.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.fragment.BaseFragment
import non.shahad.stayhomegallery.ui.recyclerviewutils.RecyclerViewPaginator
import non.shahad.stayhomegallery.utils.ext.switchToCollectionDetail


class CollectionsFragment : BaseFragment() {

    private val viewModel by injectViewModels<CollectionsViewModel>()

    private lateinit var delegate : CollectionsAdapterDelegation

    private lateinit var paginator : RecyclerViewPaginator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initRecyclerView()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.fetchCollections(1)

        viewModel.collectionsResponse.observe(viewLifecycleOwner, Observer {
            delegate.insertItems(lifecycleScope,it)

        })

        viewModel.error.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            adapter = delegate

            paginator = RecyclerViewPaginator(
                this,
                isLoading = {viewModel.isLoading},
                loadMore = {
                    viewModel.fetchCollections(it.toLong())
                },
                onLast = {false}
            ).apply {
                threshold = 1
                currentPage = 1
            }
        }
    }

    private fun initAdapter() {
        delegate = CollectionsAdapterDelegation {
            context!!.switchToCollectionDetail(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CollectionsFragment()
    }
}