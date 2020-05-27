package non.shahad.stayhomegallery.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope

import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import non.shahad.stayhomegallery.R
import non.shahad.stayhomegallery.common.activity.BaseActivity
import non.shahad.stayhomegallery.ui.home.HomeAdapterDelegation
import non.shahad.stayhomegallery.utils.ext.switchToDetail
import non.shahad.stayhomegallery.utils.ext.timberD

class SearchActivity : BaseActivity() {

    private val vm by injectViewModels<SearchViewModel>()

    private lateinit var delegate : SearchAdapterDelegation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initAdapter()
        wantToMakeStatusBarWhite(true)
        searchBar.requestFocus()
        showSoftKeyboard()

        searchBar.addTextChangedListener(watcher)

        vm.searchResult.observe(this, Observer {
            delegate.items = it
        })

        cancelBtn.setOnClickListener {
            hideSoftKeyboard()
            this.finish()
        }

    }

    private fun initAdapter(){
        delegate = SearchAdapterDelegation(
            onRootClick = { post,view ->
                switchToDetail(this,post = post,sharedElement = view)
            }
        )
        recyclerView2.adapter = delegate
    }

    private val watcher = object : TextWatcher {
        private var query = ""

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchText = s.toString().trim()

            if (searchText == query) return

            query = searchText

            lifecycleScope.launch(Dispatchers.Main){
                delay(300)
                if (query != searchText) return@launch

                vm.search(query,1)
            }


        }

        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
        = Unit
    }


}