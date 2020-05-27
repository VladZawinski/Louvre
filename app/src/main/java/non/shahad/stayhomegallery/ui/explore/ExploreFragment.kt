package non.shahad.stayhomegallery.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_explore.*
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.fragment.BaseFragment
import non.shahad.stayhomegallery.utils.ext.switchToCollectionDetail
import non.shahad.stayhomegallery.utils.ext.switchToDetail
import non.shahad.stayhomegallery.utils.ext.switchToSearchActivity
import non.shahad.stayhomegallery.utils.ext.timberD

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

        exploreSearchBar.windowToken

        exploreSearchBar.setOnClickListener{
            context?.switchToSearchActivity(requireActivity() as AppCompatActivity,it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.fetchCollection(rand(1,10))
        viewModel.collectionResponse.observe(viewLifecycleOwner, Observer {
            delegate.items = it
        })
    }

    private fun setUpRecyclerView(){
        recyclerView.apply {
            adapter = delegate
        }
    }

    private fun setUpAdapter(){
        delegate = ExploreAdapterDelegation(){
            context!!.switchToCollectionDetail(it)
        }
    }

    private fun rand(start: Int,end: Int) : Long{
        require(start <= end) {"Illegal Argument"}
        return (start..end).random().toLong()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ExploreFragment()
    }

}