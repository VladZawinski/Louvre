package non.shahad.stayhomegallery.common.itemdecoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import non.shahad.stayhomegallery.utils.display.Display

class HomeGridItemDecoration(private  val size : Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildLayoutPosition(view)
        val display = Display(parent.context!!)

//        if (position == 0 || position == 1){
            outRect.top = display.covertDpToPx(size)
//        }

        if (position % 2 == 0){
            outRect.left = display.covertDpToPx(size)
        }else outRect.right = display.convertPxToDp(size)

    }
}