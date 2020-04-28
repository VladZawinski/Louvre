package non.shahad.stayhomegallery.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_explore.*
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.fragment.BaseFragment
import non.shahad.stayhomegallery.utils.ext.timberD
import non.shahad.stayhomegallery.utils.ext.timberE


class ExploreFragment : BaseFragment() {

    private val viewModel by injectViewModels<ExploreViewModel>()

    private lateinit var delegate : ExploreAdapterDelegation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.fetchCollection(2)
        viewModel.collectionResponse.observe(viewLifecycleOwner, Observer {
            timberD("Explore_","$it")
            delegate.items = it
        })
    }

    private fun setUpRecyclerView(){
        recyclerView.apply {
            adapter = delegate
        }
    }

    private fun setUpAdapter(){
        delegate = ExploreAdapterDelegation()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ExploreFragment()
    }
}