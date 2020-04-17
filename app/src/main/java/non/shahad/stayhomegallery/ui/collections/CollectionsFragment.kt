package non.shahad.stayhomegallery.ui.collections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.fragment.BaseFragment


class CollectionsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collections, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CollectionsFragment()
    }
}