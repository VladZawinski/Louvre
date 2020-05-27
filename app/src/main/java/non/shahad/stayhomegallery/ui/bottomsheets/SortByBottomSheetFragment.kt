package non.shahad.stayhomegallery.ui.bottomsheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_sortby.*
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.utils.constants.SortByReq

class SortByBottomSheetFragment(
    private val onItemClick: (Int) -> Unit
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_sortby,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        latestGroup.setOnClickListener {
            onItemClick(SortByReq.LATEST)
            dismiss()
        }

        trendingGroup.setOnClickListener {
            onItemClick(SortByReq.TRENDING)
            dismiss()
        }

        oldestGroup.setOnClickListener {
            onItemClick(SortByReq.OLDEST)
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(context!!,theme)

    override fun getTheme(): Int = R.style.SortByBottomSheetTheme

    companion object{
        fun newInstance(onItemClick : (Int) -> Unit) = SortByBottomSheetFragment(onItemClick)
    }


}