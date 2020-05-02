package non.shahad.stayhomegallery.ui.search

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search.*
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.activity.BaseActivity

class SearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        wantToMakeStatusBarWhite(true)
        searchBar.requestFocus()
        showSoftKeyboard()

        cancelBtn.setOnClickListener {
            hideSoftKeyboard()
            this.finish()
        }
    }
}